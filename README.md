# setu-mad1-assignment-2-template

Assignment details available @ https://tutors.dev/talk/setu-mad1/topic-00-assignments/unit-2/talk-assignment-2

<ins>**API Key no longer works.**</ins>


## League Mobile App

This is a continuation of the league app from CA 1. It has now been brought to mobile! Add your favourite leagues and clubs to your phone. This app utilises backend databases and APIs to allow for a more seamless experience and greater expandability!
Please check out the App in the releases section. For more details about the workings of this app, please read on.

### Football API
This app uses RapidAPI's football-api to retrieve information on football leagues and clubs.

- '/leagues' this takes in a query param 'search' allowing for searching and retrieval of any league in their database
- '/teams' this takes in a query param 'search' allowing for searching and retrieval of any team in the database

### Room Database

Room is used for local database storage. This allows for dynamic storage of leagues and databases and no issues with scaling.

### Drill Down CRUD experience
UI allows for intuitive drill down experience. The user can add leagues, then in that league add a club. This allows for seamless management of leagues and clubs.

## References

The labs provided a lot of help while working on this, however some third party help was needed. They are listed below:
- Integration with phone gallery app - https://proandroiddev.com/jetpack-compose-new-photo-picker-b280950ba934

See the rubric PDF for a more detailed summary of this project.
