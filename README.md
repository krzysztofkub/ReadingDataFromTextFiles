# ReadingDataFromTextFiles

Instructions:
- In Main class in variable DATA paste file path,
- In ContactDao and CustomerDao change your db endpoint, db name, username and password,
- Create tables in db (if not created) with /src/main/resources/database.sql.

Assumption:
- Only two file formats: CSV and XML,
- Customer have to have fields: name, surname, city, contacts - age is optional,
- city field isn't saved to db,
- jabber format is only case-insensitive word characters.

Refactored:
- made enum for contact type
- made dbutil class
- file path as application argument
- parser only parses, saving to db is called from service
- changed dao classes as static
