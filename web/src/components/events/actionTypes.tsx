interface IEvent {
    id: number,
    title: string,
    address: string,
    date: string,
    description: string,
    speaker: string,
}

export interface IEventState {
    events: IEvent[]
}

export const GET_EVENT = 'GET_EVENT';
export const ADD_EVENT = 'ADD_EVENT';
export const DELETE_EVENT = 'DELETE_EVENT';
export const UPDATE_EVENT = 'UPDATE_EVENT';

interface GetEventAction {
    type: typeof GET_EVENT
    payload: IEvent
}

interface AddEventAction {
    type: typeof ADD_EVENT
    payload: IEvent
}

interface DeleteEventAction {
    type: typeof DELETE_EVENT
    payload: IEvent
}

interface UpdateEventAction {
    type: typeof UPDATE_EVENT
    payload: IEvent
}

export type EventActionTypes =
    GetEventAction |
    AddEventAction |
    DeleteEventAction |
    UpdateEventAction;

