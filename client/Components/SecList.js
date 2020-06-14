import React from 'react';
import './Tables.css';

export default ({ data, update }) => {
	const list = data.map((s, index) => {

		return(
			<tr key={index} onClick={() => {
				update({ active: index });
			}}>
				<td>{s.secId}</td>
				<td>{s.secName}</td>
				<td>{s.emitentTitle}</td>	
				<td>{s.numTrades}</td>
				<td>{s.closePrice}</td>
				<td>{s.openPrice}</td>
			</tr>
		);
	});

	return (
		<table>
			<thead>
				<tr>
					<th>Код бумаги</th>
					<th>Название</th>
					<th>Форма предприятия</th>
					<th>Кол-во сделок</th>
					<th>Цена закр.</th>
					<th>Цена откр.</th>
				</tr>
			</thead>
			<tbody>
				{list}
			</tbody>
		</table>
	);
};