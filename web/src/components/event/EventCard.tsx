import React from 'react';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

interface ICardProps {
    key?: any;
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

function EventCard(props: ICardProps, state: ICardState) {
    const {
        id,
        title,
        address,
        date,
        description,
        speaker,
    } = this.props;

    const {
        image,
        alt,
    } = this.state;

    return (
        <Card>
            <CardActionArea>
                <CardMedia
                    component="img"
                    image="../assets/images/bcg.jpg"
                    title={this.props.title}
                />
                <CardContent>
                    <Typography>
                        {this.props.title}
                    </Typography>
                    <Typography component="p">
                        {this.props.description}
                    </Typography>
                </CardContent>
            </CardActionArea>
            <CardActions>
                <Button size="small" color="primary">
                    Share
                </Button>
                <Button size="small" color="primary">
                    Learn More
                </Button>
            </CardActions>
        </Card>
    );
}

export default EventCard;

