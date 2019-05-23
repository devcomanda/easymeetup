import React, { Component } from 'react';
import EventCard from './EventCard';
import Grid from '@material-ui/core/Grid';
import { connect } from "react-redux";
import { IEventsProps } from './actionTypes';
import { getEvents } from './actions';

class EventsList extends React.Component<IEventsProps> {

    componentDidMount() {
        this.props.getEvents();
    }

        render() {
        return (
            <Grid
                container
                direction="row"
                justify="center"
                alignItems="center"
            >
                {this.props.events.map(( event: any) =>
                    <EventCard
                        key={event.id}
                        description={event.description}
                        title={event.title}
                        date={event.date}
                    />
                )}
            </Grid>

        );
    }
}

const mapStateToProps = (state: any) => {
    return {
      events: state.events,
    };
}

const mapDispatchToProps = (dispatch: any) => ({
    getEvents: () => {
        dispatch(getEvents())
    }
});

export default connect(mapStateToProps, mapDispatchToProps)(EventsList);
