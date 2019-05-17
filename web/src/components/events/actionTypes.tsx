export interface IEvent {
    id?: number | string,
    title: string,
    address?: string,
    date: string,
    description: string,
    speaker?: string 
}

export interface IEventsState {
    events: any[];
}

export interface IEventsProps {
    events: any[];
    getEvents: any
}

export interface IGetEvents {
    type: 'GET_EVENT';
    payload: IEventsState;
}

export interface IAddEvents {
    type: 'ADD_EVENT';
    payload: IEvent;
}

export type EventActionTypes = IGetEvents;