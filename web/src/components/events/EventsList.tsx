import React, { Component } from 'react';
import { rootReducer } from '../../rootReducer';
import { getEvent } from "./actions";

''
// import { useState, useEffect } from 'react';
import axios from 'axios';
import EventCard from './EventCard';
import Grid from '@material-ui/core/Grid';


class EventsList extends Component {

    // state = {
    //     events: [
    //         {
    //             id: 1212,
    //             title: "CopyRight Automatico 1",
    //             address: "Kiev, Main street 15",
    //             date: "12.12.2018",
    //             description: "Description Js",
    //             speaker: "Ilon Mask"
    //         },
    //         {
    //             id: 1212,
    //             title: "CopyRight Automatico 1",
    //             address: "Kiev, Main street 15",
    //             date: "12.12.2018",
    //             description: "Description Js",
    //             speaker: "Ilon Mask"
    //         },
    //         {
    //             id: 1212,
    //             title: "CopyRight Automatico 1",
    //             address: "Kiev, Main street 15",
    //             date: "12.12.2018",
    //             description: "Description Js",
    //             speaker: "Ilon Mask"
    //         }
    //     ]
    // };

    // componentDidMount() {
    //     axios.get(`http://localhost:3000/events`)
    //         .then(res => {
    //             const event = res.data;
    //             this.setState({ event });
    //         })
    // }


    render() {
        return (
            <Grid
                container
                direction="row"
                justify="center"
                alignItems="center"
            >
                {/*{ this.state.event.map(( event: any) =>*/}
                    {/*<EventCard*/}
                        {/*key={event.id}*/}
                        {/*description={event.description}*/}
                        {/*title={event.title}*/}
                        {/*date={event.date}*/}
                    {/*/>)}*/}
            </Grid>
        );
    }

    const mapStateToProps = (state: AppState) => ({
        events: state.events
    })
}

export  default  EventsList;
