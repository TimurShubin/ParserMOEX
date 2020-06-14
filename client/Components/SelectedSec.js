import React, {Component} from 'react';
import InputForm from './InputForm';

export default ({ data, active, removeTrade, updateTrade }) => {

	if(!data || !data[active]) { return (<h3>Ничего не выбрано</h3>); }

	const sec = data[active];
	
	return (

		<form id="formData">
		<table>
			<thead>
				<tr>
					<th>Дата сделки</th>
					<th>Кол-во сделок</th>
					<th>Цена закр.</th>
					<th>Цена откр.</th>
					<th>Название</th>
					<th>Форма предприятия</th>
					<th></th>
					<th></th>
				</tr>
			</thead>		
			<tbody>
				<InputForm data={sec} />
			</tbody>
		</table>
		</form>
	);
};