 
const initialState = [];

const events = (state = initialState, action: any) => {
  switch (action.type) {
    case 'GET_EVENT':
      return  action.payload;
    case 'ADD_EVENT':
      return Object.assign({}, state, {
        ...state
      });

    default:
      return state;
  }
}

export default events;
