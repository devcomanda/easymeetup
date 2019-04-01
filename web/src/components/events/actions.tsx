import { IEventState , ADD_EVENT, GET_EVENT, DELETE_EVENT, UPDATE_EVENT} from './actionTypes'

export function addEvent(newEvent: IEvent) {
    return {
        type: ADD_EVENT,
        payload: newEvent
    }
}

export function getEvent(newEvent: IEvent) {
    axios.get(`http://localhost:3000/events`)
    .then(res => {
        const newEvent: IEventState  = res.data;
    });

    return {
        type: GET_EVENT,
        payload: newEvent
    }
}

export function deleteEvent(newEvent: IEvent) {
    return {
        type: DELETE_EVENT,
        payload: newEvent
    }
}

export function updateEvent(newEvent: IEvent) {
    return {
        type: UPDATE_EVENT,
        payload: newEvent
    }
}
