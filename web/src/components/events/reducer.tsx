import { IEventState, ADD_EVENT, GET_EVENT, DELETE_EVENT, UPDATE_EVENT } from "./actionTypes";

const initialState: IEventState = {
    events: [],
}

export function eventReducer(state=initialState, action) {
    switch(action.type) {
        case 'GET_EVENT':
            return state;
        case 'ADD_EVENT':
            return state;
        case 'DELETE_EVENT':
            return state;
        case 'UPDATE_EVENT':
            return state;
        default:
            return state;
    }
};




