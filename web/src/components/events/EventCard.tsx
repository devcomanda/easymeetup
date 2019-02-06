import React from 'react';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import './event.scss';
import  bcg from '../../assets/images/bcg.jpg';

interface ICardProps {
    id?: number;
    title: string;
    address?: string;
    date?: string;
    description: string;
    speaker?: string;
}

interface ICardState {
    image: string;
    title: string;
}

const EventCard = function (props: ICardProps, state: ICardState) {
    const {
        id,
        title,
        address,
        date,
        description,
        speaker,
    } = props;

    const {
        image,
    } = state;

    return (
        <Card className="card">
            <CardActionArea>
                <CardMedia
                    component="img"
                    image={bcg}
                    title={props.title}
                />
                <CardContent>
                    <div className="card-event-date">
                        {props.date}
                    </div>
                    <Typography className="card-event-title">
                        {props.title}
                    </Typography>
                    <Typography component="p">
                        {props.description}
                    </Typography>
                </CardContent>
            </CardActionArea>
            <CardActions>
                <Button className="btn-registration" variant="contained" size="small" color="primary">
                    Registration
                </Button>
                <Button variant="contained" size="small" color="primary">
                    Edit event
                </Button>
            </CardActions>
        </Card>
    );
};

export default EventCard;
