import React, { Component } from 'react';
// import { useState, useEffect } from 'react';
import axios from 'axios';
import EventCard from  '../event/EventCard';


class EventList extends Component {

    state = {
        event: []
    };

    componentDidMount() {
        axios.get(`http://localhost:3001/event`)
            .then(res => {
                const event = res.data;
                this.setState({ event });
            })
    }


    render() {
        return (
            <ul>
                { this.state.event.map(event => <EventCard key={event.id}>{...event}</EventCard>)}
            </ul>
        );
    }
}

export  default  EventList;
