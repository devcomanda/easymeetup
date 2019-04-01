import { eventReducer } from './components/events/reducer';
import {combineReducers} from "redux";

export const rootReducer = combineReducers({
    events: eventReducer
});

