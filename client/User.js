import React from 'react';

export default class User extends React.Component {

	constructor(props) {
		super(props);
	}

	render() {
		return (
			<h1>User {this.props.profile.email} </h1>
		);
	}

}