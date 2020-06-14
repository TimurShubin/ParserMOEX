import React from 'react';
import API from "../Utils/API";

export default class InputForm extends React.Component {

	constructor(props) {
		super(props);
		this.state = { value: '' };
		this.inputChange = this.inputChange.bind(this);
	}

	inputChange(event) {
		this.setState({value: event.target.value});
	}

	async updateTrade(id) {
		const formData = document.getElementById("formData");
	    let dataObj = {
	    	'tradeDate': formData.tradeDate.value,
	    	'numTrades': formData.numTrades.value,
	    	'closePrice': formData.closePrice.value,
	    	'openPrice': formData.openPrice.value,
	    	'secName': formData.secName.value,
	    	'emitentTitle': formData.emitentTitle.value,
	    	'secId': formData.secId.value,
	    	'tradeId': formData.tradeId.value
	    }
	    console.log(JSON.stringify(dataObj));
	    const response = await API.post('/update/' + id, dataObj)
	            .then((response) => {
	              console.log('строка изменена')
	            });
	}
	
	async removeTrade(id) {
	    const response = await API.get('/remove/' + id)
          .then((response) => {
	         console.log('строка удалена')
          });
	}

	render() {
		return (
			<tr>
				<td><input type="text" name="tradeDate"  onChange={this.inputChange} value={this.props.data.tradeDate} placeholder={this.props.data.tradeDate} />
				<input type="text" name="secId"  onChange={this.inputChange} value={this.props.data.secId} hidden />
				<input type="text" name="tradeId"  onChange={this.inputChange} value={this.props.data.tradeId} hidden /></td>
				<td><input type="text" name="numTrades" onChange={this.inputChange}  value={this.props.data.numTrades} placeholder={this.props.data.numTrades} /></td>
				<td><input type="text" name="closePrice" onChange={this.inputChange} value={this.props.data.closePrice} placeholder={this.props.data.closePrice} /></td>
				<td><input type="text" name="openPrice" onChange={this.inputChange} value={this.props.data.openPrice} placeholder={this.props.data.openPrice} /></td>
				<td><input type="text" name="secName" onChange={this.inputChange}  value={this.props.data.secName} placeholder={this.props.data.secName} /></td>
				<td><input type="text" name="emitentTitle" onChange={this.inputChange}  value={this.props.data.emitentTitle} placeholder={this.props.data.emitentTitle} /></td>
				<td><button onClick={() => { this.updateTrade(this.props.data.tradeId) } }>Редактировать</button></td>
				<td><button onClick={() => { this.removeTrade(this.props.data.tradeId) } }>Удалить</button></td>
			</tr>
		);
	}

}