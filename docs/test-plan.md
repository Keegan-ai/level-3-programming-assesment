# Plan for Testing the Program

The test plan lays out the actions and data I will use to test the functionality of my program.

Terminology:

- **VALID** data values are those that the program expects
- **BOUNDARY** data values are at the limits of the valid range
- **INVALID** data values are those that the program should reject

---

## Test Name: Movement

In this test im going to see if i can make the player move from room to room

### Data to use: MutableMapOfString

I am going to use this to generally shape my map and then im going to use north, south, east and west for me to call it easily and move my player.
I am also going to add more stuff which maybe make my game feel a bit better. I will write in this test result again after 2 weeks days


### Expected Test Result

I expect my player to move from one room to another using the buttons i implemented.

---

## Test Name:Item pickup and second dialogue for rooms

In this test i made it so that when my character enters a certain room he can pick or search the room for a item. i will also make it so that when the player enters the room a second time new dialogue pops up.

### Data to use:MutableListOfString and some classes

Im going to use this data to make it so that if the player searches a room he gets a tool and that gets added to his "inventory"
and make some class statements to make it so that when the player first visits it shows original dialogue but if enters again new dialogue show up.

### Expected Test Result

I expect that if my player goes into a room that says search the player would be able to press yes and search the room 
acquiring a tool and also if the player enters the room for a second time i expect extra dialogue to pop up.

---


