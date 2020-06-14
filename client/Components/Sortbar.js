import React, {Component} from 'react';

export default class Sortbar extends Component {
	constructor(props) {
		super(props);
		this.sorted = { secName: true, openPrice: true, closePrice: true };
	}

	sort(type) {

		const { update, data } = this.props;
		const isSorted = this.sorted[type];

		let dir = isSorted ? 1 : -1;

		const sorted = [].slice.call(data).sort((a, b) => {
			if(a[type] === b[type]) { return 0; }
			return a[type] > b[type] ? dir : dir * -1;
		});

		this.sorted[type] = !isSorted;

		update({
			data: sorted,
			active: 0
		});

	}

	render() {
		return(
			<div>
				Сортировать по: &nbsp;

				<button onClick={() => this.sort('secName')}>
					Название
				</button>

				<button onClick={() => this.sort('openPrice')}>
					Цена откр.
				</button>

				<button onClick={() => this.sort('closePrice')}>
					Цена закр.
				</button>

			</div>
		);
	}
}