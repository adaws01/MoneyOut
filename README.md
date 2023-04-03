# MoneyOut (A Finances Application)

A tool for students to engage with their finances,
view analytics, and budget intuitively. 

## Why and Who?

I am not majoring in finance, but I am a fan of optimizing what little
money I have as a starving student. I have wished for answers to 
questions such as: 

- "where in the city should I shop for groceries to 
minimize my spending?"
- "Where am I most likely to buy things that I don't
need? What are they?"
- "How much should I put into savings each month? Invest?" or
- "How does my spending vary month to month?"

I believe that a lot of students have similar questions. I have seen 
others implement complex systems of Excel spreadsheets and forms, 
and countless hours of research. I doubt I am the only one whose questions
remain unaddressed due to ignorance, or simple apathy. This application
will offer insight into financial habits, provide systems to help 
optimize and implement budgeting, and eliminate unnecessary spending 
habits.

## What will this application do?

Suited to the needs of UBC Vancouver students like myself, this app will
make use of an intuitive data entry system that (locally) gathers information 
on one's personal finance. It will save basic data including time,
location, quantities of purchased goods, perceived utility, cost, and possibly 
more. The application will then allow the user access to an extensive set
of views of their financial situation, such that they may reflect, and
make informed decisions on their future purchases. Such views might
include where in the city they spend the most money? How prices of common
items vary across shops? It could possibly even generate strategic 
shopping lists suited to various sections of the city.

## User Stories

"As a user, I want to be able to..."

- Add a transaction to my transaction history
- Modify a previous transaction's information (or delete it)
- View a list of my complete transaction history
- Update my balance
- View my balance (current/projected)
- View how many transactions I have made (since X time or in X neighbourhood).
- Add a budget or a recurrent cost (to predict my balance) 
(budgets never subtracted from balance, recurrent subtracted from balance on date of cost)
- Categorize my transactions and have them work towards my budgets 
(ex. budget $300 towards food each month -> transactions tagged "food" will subtract from food budget)
- View percentages of what I spend money on
- View shops that I spend the most money at

---


### P2

- Save my recorded Transactions, Locations, and Account information to file (if I so choose)
- Load my recorded Transactions, Locations, and Account information from file (if I so choose)

---

### P3

### Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by:
  - First opening the Locations menu, and selecting 'New Location'
  - Entering location information as prompted (note, some transactions require at least one location to be logged)
- You can generate the second required action related to adding Xs to a Y by:
  - Opening the Locations menu, and selecting 'Modify Location'
  - Entering location information as prompted (there must already be a location logged)

^Note: The same actions are also available within the Transactions menu, and some similar actions in the Account menu.

- You can locate my visual component by:
  - Relaunching the app to find a splash screen
- You can save the state of my application by:
  - Selecting 'Save to File' from the main menu
- You can reload the state of my application by:
  - Selecting 'Load from File' from the main menu