import React, { Component } from 'react';
// import { useState, useEffect } from 'react';
import axios from 'axios';
import EventCard from './/EventCard';
import Grid from '@material-ui/core/Grid';


class EventsList extends Component {

    state = {
        event: []
    };

    componentDidMount() {
        axios.get(`http://localhost:3000/event`)
            .then(res => {
                const event = res.data;
                this.setState({ event });
            })
    }


    render() {
        return (
            <Grid
                container
                direction="row"
                justify="center"
                alignItems="center"
            >
                { this.state.event.map(event =>
                    <EventCard
                        key={event.id}
                        description={event.description}
                        title={event.title}
                        date={event.date}
                    />)}
            </Grid>
        );
    }
}

export  default  EventsList;
