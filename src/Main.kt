/**
 * =====================================================================
 * Programming Project for NCEA Level 3, Standard 91906
 * ---------------------------------------------------------------------
 * Project Name:   Space Terror
 * Project Author: Keegan Gous
 * GitHub Repo:    https://github.com/Keegan-ai/level-3-programming-assesment
 * ---------------------------------------------------------------------
 * Notes:
 * PROJECT NOTES HERE
 * =====================================================================
 */

//    "You Journey Begins on a space station orbiting earth." +
//    "You are part of a crew called the SS Galactic where you've been assigned to test various chemicals which were too dangerous to use on earth." +
//    "One Day a explosion happened and the next thing you knew you woke up in the crew quarters." +

import com.formdev.flatlaf.FlatDarkLaf
import java.awt.*
import java.awt.event.*
import javax.swing.*


/**
 * Launch the application
 */
fun main() {
    FlatDarkLaf.setup()     // Flat, dark look-and-feel
    val app = App()         // Create the app model
    MainWindow(app)         // Create and show the UI, using the app model
}


//Room class helps connect the rooms and makes it possible to create and put descriptions in them
class Room(val name: String, val description: String) {
    val connections = mutableMapOf<String, Room>()
    //Checks if the player has visited the room the 2nd time

    var visited = false  // Default to false (not visited)

    fun connect(direction: String, room: Room) {
        connections[direction] = room
    }
}

/**
 * The application class (model)
 * This is the place where any application data should be
 * stored, plus any application logic functions
 */
class App() {
    var foundTool = false // This tracks if the player has already found the tool
    val rooms = mutableMapOf<String, Room>()// Keeps track of the rooms
    var currentRoom: Room? = null//Doesn't know where the player is yet, but it will update once the player is in a room
    var gameStarted: Boolean = false  // Flag to check if the game has started\
    val inventory = mutableListOf<String>()  // Holds items the player picks up



    init {
        setupRooms()
    }

    // This holds the info of a certain room.
    private fun setupRooms() {
        val crewQuarters = Room("Crew Quarters", "You begin to wake up in the cabin quarters confused on what happen    because last thing you knew is were sleeping. " +
                "As you start to come to your senses a      alarm beeps saying Oxygen supply low advised to wear a suit until repa..zzz.zz..z are       done." +
                " After hearing that you begin to put on a suit ")
        val hallway = Room("Hallway", "You enter the hallway and see a sign saying Maintenance     Room south and Ahead is the Kitchen ")
        val controlRoom = Room("Control Room", "You enter the main control room. " +
                "You look around and see that the control room is dead, its systems offline. A few terminals sputter weakly,          looping a broken distress signal: '...Mayday... impact detected... system failure...'  .")
        val kitchen = Room("Kitchen", "You enter the kitchen.The kitchen is a mess. Cabinets hang open, their contents spilled across the floor. " +
                "A broken tray of food floats near the ceiling, evidence of the sudden impact. " +
                "The power is out, leaving the room cold and lifeless, the   scent of stale rations lingering in the air.")
        val Escape_Pod = Room("Escape Pods","As You enter the escape pod room. You see that all    escape pods are gone." +
                "You begin to panic thinking there isn't one here for you but luckily you find one unscathed. But the wires you see that the control panel is broken." +
                "You tell   yourself that you need to find something to fix it...................................................................." +
                "Do you wish to repair: Press yes to repair or No ")
        val Entrance = Room("Entrance"," You wonder where you are and you suddenly see a sign  saying Escape Pods North and Section 1 South." )
        val Section_1 =Room("Section 1","You enter section 1 which is on the west most side of the    space station." +
                " You look at the sign to see where you want to go next and the sign says     South is where the Garbage Disposel and east is To go to the Control Room  ")
        val Section_2 = Room("Section 2","You enter section 2 which is on the east most side of the   space station." +
                "You look at the sign to see where you want to go next and the sign says     South is where the Kitchen is and West is to go to the Control Room.")
        val Garbage_Disposel = Room("Garbage Disposel","As you enter the Garbage Disposel room you look around and see trash lying everywhere which is not suprising." +
                "You see that there is a       door going east towards the Hallway")
        val Maintenace_room = Room( "Maintenace Room","You enter maintenance room and see that it is in disarray. " +
                "Loose wires hang from the ceiling, sparking occasionally. " +
                "Toolboxes have           toppled over, their contents scattered across the floor." +
                " A thick smell of burnt circuits          lingers in the air: Do you wish to search the room")

        // Cotrols where the player can go and where he can go while also serving as connection points
        crewQuarters.connect("West", Entrance)
        crewQuarters.connect("South", Section_2)
        Entrance.connect("North", Escape_Pod)
        Entrance.connect("East", crewQuarters)
        Entrance.connect("South", Section_1)
        Section_1.connect("North", Entrance)
        Section_1.connect("East", controlRoom)
        Section_1.connect("South", Garbage_Disposel)
        Garbage_Disposel.connect("North", Section_1)
        Garbage_Disposel.connect("East", hallway)
        hallway.connect("West", Garbage_Disposel)
        hallway.connect("South", Maintenace_room)
        hallway.connect("East", kitchen)
        Maintenace_room.connect("North", hallway)
        kitchen.connect("West", hallway)
        kitchen.connect("North", Section_2)
        Section_2.connect("South", kitchen)
        Section_2.connect("West", controlRoom)
        Section_2.connect("North", crewQuarters)
        controlRoom.connect("West", Section_1)
        controlRoom.connect("East", Section_2)
        Escape_Pod.connect("South", Entrance)


        // Creates the room
        rooms["Crew Quarters"] = crewQuarters
        rooms["Hallway"] = hallway
        rooms["Control Room"] = controlRoom
        rooms["Kitchen"] = kitchen
        rooms["Escape Pod"] = Escape_Pod
        rooms["Entrance"] = Entrance
        rooms["Section 1"] = Section_1
        rooms["Section 2"] = Section_2
        rooms["Garbage Disposel"] = Garbage_Disposel
        rooms["Maintenace Room"] = Maintenace_room


        currentRoom = crewQuarters
    }
}

/**
 * Main UI window (view)
 * Defines the UI and responds to events
 * The app model should be passwd as an argument
 */
class MainWindow(val app: App) : JFrame(), ActionListener {

    // This function handles picking up the tool repair kit in the Maintenance Room
    fun handleToolPickup() {
        if (!app.foundTool && app.currentRoom?.name == "Maintenace Room") {
            // Shows a message asking the player if they want to search for the tool
            UI.text = "You see tools scattered around. Do you want to pick them up? Press Yes or No."
        }
    }



    // Fields to hold the UI elements
//private lateinit var Escape_Pod: JLabel
//private lateinit var Entrance: JLabel
//private lateinit var Section_1: JLabel
//private lateinit var Section_2: JLabel
//private lateinit var Garbage_Disposel: JLabel
//private lateinit var Control_centre: JLabel
//private lateinit var Cabin_quarters: JLabel
//private lateinit var Kitchen: JLabel
//private lateinit var Corrider: JLabel
//private lateinit var Maintenace_room: JLabel
    private lateinit var location_1: JLabel
    private lateinit var North_Route: JLabel
    private lateinit var South_Route: JLabel
    private lateinit var West_Route: JLabel
    private lateinit var East_Route: JLabel
    private lateinit var Map: JLabel
    private lateinit var UI: JTextArea
    private lateinit var move_Forward: JButton
    private lateinit var move_Backward: JButton
    private lateinit var move_Left: JButton
    private lateinit var move_Right: JButton
    private lateinit var yes: JButton
    private lateinit var No: JButton

    /**
     * Configure the UI and display it
     */
    init {
        configureWindow()               // Configure the window
        addControls()                   // Build the UI

        setLocationRelativeTo(null)     // Centre the window
        isVisible = true                // Make it visible

        updateView()                    // Initialise the UI
    }

    /**
     * Configure the main window
     */
    private fun configureWindow() {
        title = "Kotlin Swing GUI Demo"
        contentPane.preferredSize = Dimension(1000, 500)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
        layout = null

        pack()
    }

    /**
     * Populate the UI with UI controls
     */
    private fun addControls() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 36)

        location_1 = JLabel("Room")
        location_1.bounds = Rectangle(135, 195, 100, 100)
        location_1.font = baseFont
        add(location_1)

        North_Route = JLabel("North")
        North_Route.bounds = Rectangle(135, 105, 100, 100)
        North_Route.font = baseFont
        add(North_Route)

        South_Route = JLabel("South")
        South_Route.bounds = Rectangle(135, 290, 100, 100)
        South_Route.font = baseFont
        add(South_Route)

        East_Route = JLabel("East")
        East_Route.bounds = Rectangle(10, 195, 100, 100)
        East_Route.font = baseFont
        add(East_Route)

        West_Route = JLabel("West")
        West_Route.bounds = Rectangle(260, 195, 100, 100)
        West_Route.font = baseFont
        add(West_Route)

        Map = JLabel("Map")
        Map.horizontalAlignment = SwingConstants.CENTER
        Map.bounds = Rectangle(35, 25, 300, 100)
        Map.font = baseFont
        add(Map)

        UI = JTextArea("Welcome to the game of Space Terror:" +
                "Press Yes to continue ")
        UI.bounds = Rectangle(400, 50, 580, 200)
        UI.foreground = Color.WHITE
        UI.background = Color.BLACK
        UI.font = Font(Font.SANS_SERIF, Font.PLAIN, 14)
        UI.lineWrap = true
        add(UI)



        move_Forward = JButton("^")
        move_Forward.bounds = Rectangle(495, 265, 75, 75)
        move_Forward.font = baseFont
        move_Forward.addActionListener(this)     // Handle any clicks
        add(move_Forward)

        move_Backward = JButton("v")
        move_Backward.bounds = Rectangle(495, 415, 75, 75)
        move_Backward.font = baseFont
        move_Backward.addActionListener(this)     // Handle any clicks
        add(move_Backward)

        move_Left = JButton("<")
        move_Left.bounds = Rectangle(420, 340, 75, 74)
        move_Left.font = baseFont
        move_Left.addActionListener(this)     // Handle any clicks
        add(move_Left)

        move_Right = JButton(">")
        move_Right.bounds = Rectangle(570, 340, 75, 75)
        move_Right.font = baseFont
        move_Right.addActionListener(this)     // Handle any clicks
        add(move_Right)

        yes = JButton("Yes")
        yes.bounds = Rectangle(750, 315, 100, 100)
        yes.font = baseFont
        yes.addActionListener(this)     // Handle any clicks
        add(yes)

        No = JButton("No")
        No.bounds = Rectangle(875, 315, 100, 100)
        No.font = baseFont
        No.addActionListener(this)     // Handle any clicks
        add(No)

        //Disables the buttons until i decide when to re-enable them
        move_Forward.isEnabled = false
        move_Backward.isEnabled = false
        move_Left.isEnabled = false
        move_Right.isEnabled = false

    }


    /**
     * Update the UI controls based on the current state
     * of the application model
     */
    fun updateView() {
        if (!app.gameStarted) {
            UI.text = "Welcome to Space Terror: Press No to read the rules or Press Yes to skip the rules"
        } else {
            val currentRoom = app.currentRoom
            val desc = currentRoom?.description ?: "Unknown location"
            UI.text = "${currentRoom?.name}. $desc"

            // Check if we're in the Maintenance Room and haven't found the tool yet
            if (currentRoom?.name == "Maintenace Room" && !app.foundTool) {
                handleToolPickup() // Ask player to pick up the tool
            }
        }
    }

    /**
     * Handle any UI events (e.g. button clicks)
     * Usually this involves updating the application model
     * then refreshing the UI view
     */
    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {
            // Handling the 'Yes' button click:
            yes -> {
                if (!app.gameStarted) {
                    app.gameStarted = true
                    enableMovementButtons()
                    app.currentRoom = app.rooms["Crew Quarters"]
                    updateView()
                } else {
                    // Check if player is in Maintenance Room and hasn't picked up the tool yet
                    if (app.currentRoom?.name == "Maintenace Room" && !app.foundTool) {
                        app.foundTool = true
                        app.inventory.add("Tools")
                        UI.text = "You pick up the tools! It has been added to your inventory."
                    } else {
                        UI.text = "You decide to continue on your journey."
                    }
                }
            }

            // Handling the 'No' button click:
            No -> {
                if (!app.gameStarted) {
                    UI.text = "Space Terror: The goal of the game is to try and escape a space which unknowingly had a impact of a unknown object.\n" +
                            "You must move between rooms and try to find something to help you escape..\n" +
                            "Also Thx for trying out my game :).\n" +
                            "Press 'Yes' to start the game."
                } else {
                    // If in Maintenance Room and say no to tool
                    if (app.currentRoom?.name == "Maintenace Room" && !app.foundTool) {
                        UI.text = "You chose not to search for tools right now."
                    } else {
                        UI.text = "You decide to stay put for now."
                    }
                }
            }

            //All the movement buttons now check if the player has gone in the room again it will flag check it

            // Handling the 'Move Forward' (North) button click:
            move_Forward -> {
            val nextRoom = app.currentRoom?.connections?.get("North")
            if (nextRoom != null) {
                app.currentRoom = nextRoom
                val firstVisit = !nextRoom.visited
                nextRoom.visited = true
                UI.text = if (firstVisit) {
                    "You moved north to ${nextRoom.name}. ${nextRoom.description}"
                } else {
                    "You moved north to ${nextRoom.name}. You remember your previous visit here. ${nextRoom.description}"
                }
            } else {
                UI.text = "You can't go that way."
            }
        }

            // Handling the 'Move Backward' (South) button click:
            move_Backward ->  {
            val nextRoom = app.currentRoom?.connections?.get("South")
            if (nextRoom != null) {
                app.currentRoom = nextRoom
                val firstVisit = !nextRoom.visited
                nextRoom.visited = true
                UI.text = if (firstVisit) {
                    "You moved north to ${nextRoom.name}. ${nextRoom.description}"
                } else {
                    "You moved north to ${nextRoom.name}. You remember your previous visit here. ${nextRoom.description}"
                }
            } else {
                UI.text = "You can't go that way."
            }
        }

            // Handling the 'Move Left' (East) button click:
            move_Left -> {
                val nextRoom = app.currentRoom?.connections?.get("East")
                if (nextRoom != null) {
                    app.currentRoom = nextRoom
                    val firstVisit = !nextRoom.visited
                    nextRoom.visited = true
                    UI.text = if (firstVisit) {
                        "You moved north to ${nextRoom.name}. ${nextRoom.description}"
                    } else {
                        "You moved north to ${nextRoom.name}. You remember your previous visit here. ${nextRoom.description}"
                    }
                } else {
                    UI.text = "You can't go that way."
                }
            }

            // Handling the 'Move Right' (West) button click:
            move_Right -> {
                val nextRoom = app.currentRoom?.connections?.get("West")
                if (nextRoom != null) {
                    app.currentRoom = nextRoom
                    val firstVisit = !nextRoom.visited
                    nextRoom.visited = true
                    UI.text = if (firstVisit) {
                        "You moved north to ${nextRoom.name}. ${nextRoom.description}"
                    } else {
                        "You moved north to ${nextRoom.name}. You remember your previous visit here. ${nextRoom.description}"
                    }
                } else {
                    UI.text = "You can't go that way."
                }
            }
        }
    }
    //
    //This makes it so that when my yes button is clicked it re-enables the rest of the buttons again
    fun enableMovementButtons() {
        move_Forward.isEnabled = true
        move_Backward.isEnabled = true
        move_Left.isEnabled = true
        move_Right.isEnabled = true
    }

    fun item_pickup(){

    }

    fun Escape_function(){

    }

}


