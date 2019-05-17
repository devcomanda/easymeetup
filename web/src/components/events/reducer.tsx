 
const initialState = [
    {
      "id": 12737812,
      "title": "Local CopyRight Automatico 1",
      "address": "Kiev, Main street 15",
      "date": "12.12.2018",
      "description": "Description Js",
      "speaker": "Local Ilon Mask"
    },
    {
      "id": 1237467,
      "title": "Local CopyRight Automatico 2",
      "address": "Kiev, Main street 15",
      "date": "13.12.2018",
      "description": "Description Java",
      "speaker": "Ilon Mask"
    }
];

const events = (state = initialState, action: any) => {
  switch (action.type) {
    case 'GET_EVENT':
      return [
        ...state,
        action.payload
      ];      
    case 'ADD_EVENT':
      return Object.assign({}, state, {
        ...state
      });

    default:
      return state;
  }
}

export default events;