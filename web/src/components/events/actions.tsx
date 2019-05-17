import axios from 'axios';

export const getEvents = () => (dispatch: any) =>{  
    // setTimeout(() => {
    //     console.log('got events');
    //     dispatch({type: 'GET_EVENT', payload: [] })
    // }, 2000)
    return axios.get(`http://localhost:3000/events`)
      .then( (events: any) => {
        dispatch({type: 'GET_EVENT', payload: events})
    });
}