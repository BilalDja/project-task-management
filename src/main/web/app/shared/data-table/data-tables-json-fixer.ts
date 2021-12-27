const DataTablesJsonFixer = (dataTablesParameters: any):any => {
  let payload: any = {};

  payload['draw'] = dataTablesParameters.draw;
  for (let i = 0; i < dataTablesParameters.columns.length; i++) {
    payload['columns['+i+'].data'] = dataTablesParameters.columns[i].data;
    payload['columns['+i+'].name'] = dataTablesParameters.columns[i].name;
    payload['columns['+i+'].searchable'] = dataTablesParameters.columns[i].searchable;
    payload['columns['+i+'].orderable'] = dataTablesParameters.columns[i].orderable;
    payload['columns['+i+'].search.value'] = dataTablesParameters.columns[i].search.value;
    payload['columns['+i+'].search.regex'] = dataTablesParameters.columns[i].search.regex;
  }
  for (let i = 0; i < dataTablesParameters.order.length; i++) {
    payload['order['+i+'].column'] = dataTablesParameters.order[i].column;
    payload['order['+i+'].dir'] = dataTablesParameters.order[i].dir;
  }
  payload['start'] = dataTablesParameters.start;
  payload['length'] = dataTablesParameters.length;
  payload['search.value'] = dataTablesParameters.search.value;
  payload['search.regex'] = dataTablesParameters.search.regex;
  return payload;
}

export default DataTablesJsonFixer;
