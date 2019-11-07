import axios from 'axios';

export const getEvents = () => (dispatch: any) =>{  
    return axios.get(`http://localhost:3000/events`)
      .then( (events: any) => {
        dispatch({type: 'GET_EVENT', payload: events.data})
    });
}
