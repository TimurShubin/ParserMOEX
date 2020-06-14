import React from "react";
import API from "./Utils/API";
import SecList from "./Components/SecList";
import SelectedSec from "./Components/SelectedSec";
import Sortbar from './Components/Sortbar';

class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      data: [],
      active: 0
    };
  }

  async componentDidMount() {
    try {
        const response = await API.get('/getHistory')
          .then((response) => {
            this.setState({
              data: response.data
            });
          });
    } catch(e) {
      console.log(e);
    }
  }

  async handleDrop() {
    const xmlfiles = document.getElementById("xmlfiles");
    const formData = new FormData(xmlfiles);

    for (let obj of formData.entries()) {
      if (obj.type == "text/xml") {
        formData.append('filename', obj);
      }
    }

    const response = await API.post('/uploadXML', formData)
      .then((response) => {
        try {
          const response = API.get('/getHistory')
            .then((response) => {
              this.setState({
                data: response.data
              });
            });
        } catch(e) {
          console.log(e);
        }
      });

  }

  updateData(config) {
    this.setState(config);
  }

  render() {
    return (
      <div className="App">
          
          <form onSubmit={this.handleDrop.bind(this)} id="xmlfiles" encType="multipart/form-data">
            <input type="file" name="file" multiple />
            <button type='button' onClick={this.handleDrop.bind(this)}>отправить</button>
          </form>
          <br />
          <SelectedSec data={this.state.data} active={this.state.active} />
          <br />
          <Sortbar data={this.state.data} update={this.updateData.bind(this)} />
          <SecList data={this.state.data} update={this.updateData.bind(this)} />

      </div>
    )
  }

}

export default App;
