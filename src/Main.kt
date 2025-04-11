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
    FlatDarkLaf.setup()     // Set up a flat, dark look-and-feel
    val app = App()         // Create the app model
    MainWindow(app)         // Create and show the UI, using the app model
}


// Room class helps connect rooms and sets descriptions (unchanged)
class Room(val name: String, val description: String) {
    val connections = mutableMapOf<String, Room>()
    var visited = false  // Flag to check if the room has been visited before

    fun connect(direction: String, room: Room) {
        connections[direction] = room
    }
}

/**
 * The application class (model)
 * Stores game data and holds game logic functions.
 */
class App() {
    var foundTool = false // Tracks if the player has already found the tool
    val rooms = mutableMapOf<String, Room>() // Keeps track of the rooms
    var currentRoom: Room? = null // Current room; updated as player moves
    var gameStarted: Boolean = false  // Flag to check if the game has started
    val inventory = mutableListOf<String>()  // Holds items the player picks up

    init {
        setupRooms()  // Build and connect all rooms
    }

    // Sets up the rooms and connects them
    private fun setupRooms() {
        val crewQuarters = Room("Crew Quarters",
            "\nYou wake up in the cabin quarters confused on what happened because last thing you     knew is you were sleeping. " +
                    "As you start to come to your senses, an alarm beeps: Oxygen supply low advised to wear a suit until repairs are done. " +
                    "After hearing that message, you   begin to put on a suit.")
        val hallway = Room("Hallway",
            "You enter the hallway and see a sign saying:\nMaintenance Room south and Ahead is the Kitchen.")
        val controlRoom = Room("Control Room",
            "You enter the main control room. You look around and see that the control room is dead,   its systems offline. " +
                    "A few terminals sputter weakly, looping a broken distress signal:\n'...Mayday... impact detected... system failure...'.")
        val kitchen = Room("Kitchen",
            "You enter the kitchen. The kitchen is a mess. Cabinets hang open, their contents spilled   across the floor. " +
                    "A broken tray of food floats near the ceiling, evidence of the sudden \nimpact. " +
                    "The power is out, leaving the room cold and lifeless, the scent of stale rations \nlingering in the air.")
        // The Escape Pod room will serve as the ending if the player has the Tools.
        val Escape_Pod = Room("Escape Pods",
            "As you enter the escape pod room, you see that all escape pods are gone. " +
                    "You begin to panic, thinking there isn't one here for you, but luckily you find one unscathed. " +
                    "However,   the wires you see indicate that the control panel is broken. " +
                    "You tell yourself that if you       had tools, you might be able to fix the Escape pod.",
        )
        val Entrance = Room("Entrance",
            "You wonder where you are and suddenly see a sign saying: \n Escape Pods North and Section 1 South.")
        val Section_1 = Room("Section 1",
            "You enter section 1 which is on the west-most side of the space station. " +
                    "A sign indicates: South is where the Garbage Disposal is and east is to go to the Control Room.")
        val Section_2 = Room("Section 2",
            "You enter section 2 which is on the east-most side of the space station. " +
                    "A sign indicates: South is where the Kitchen is and West is to go to the Control Room.")
        val Garbage_Disposel = Room("Garbage Disposel",
            "As you enter the Garbage Disposel room, you look around and see trash lying\neverywhere which is not surprising. " +
                    "You see that there is a door going east towards the   Hallway.")
        val Maintenace_room = Room("Maintenace Room",
            "You enter the maintenance room and see that it is in disarray. " +
                    "Loose wires hang from the ceiling, sparking occasionally. " +
                    "Toolboxes have toppled over, their contents\nscattered across the floor. " +
                    "A thick smell of burnt circuits lingers in the air:\nDo you wish to search the room?")

        // Connect the rooms
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

        // Save rooms in the map
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

        currentRoom = crewQuarters // Set starting room
    }
}

/**
 * Main UI window (view)
 * Defines the UI and responds to events.
 * The app model is passed as an argument.
 */
class MainWindow(val app: App) : JFrame(), ActionListener {

    // This function handles picking up the tool repair kit in the Maintenance Room.
    fun handleToolPickup() {
        if (!app.foundTool && app.currentRoom?.name == "Maintenace Room") {
            // Ask the player if they want to pick up the tool repair kit.
            UI.text = "You see tools scattered around. Do you want to pick them up? Press Yes or No."
        }
    }

    // UI element fields.
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
    private lateinit var no: JButton

    /**
     * Configure the UI and display it.
     */
    init {
        configureWindow() // Setup window basics.
        addControls()     // Create the UI controls.

        setLocationRelativeTo(null)  // Centre the window.
        isVisible = true             // Make it visible.

        updateView() // Initialize the UI.
    }

    /**
     * Configure the main window.
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
     * Populate the UI with controls.
     */
    private fun addControls() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 36)
        var Arrow_Up = ImageIcon("src/Images/Arrow Up.png").image
        var Arrow_Down = ImageIcon("src/Images/Arrow Down.png").image
        var Arrow_Left = ImageIcon("src/Images/Arrow Left.png").image
        var Arrow_Right = ImageIcon("src/Images/Arrow Right.png").image
        var Yes = ImageIcon("src/Images/Yes.png").image
        var No = ImageIcon("src/Images/No.png").image

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

        UI = JTextArea("Welcome to the game of Space Terror: Press Yes to continue")
        UI.bounds = Rectangle(400, 50, 580, 200)
        UI.foreground = Color.WHITE
        UI.background = Color.BLACK
        UI.font = Font(Font.SANS_SERIF, Font.PLAIN, 14)
        UI.lineWrap = true
        add(UI)

        move_Forward = JButton()
        move_Forward.bounds = Rectangle(495, 265, 75, 75)
        move_Forward.font = baseFont
        move_Forward.background = Color(0,0,0,0)
        move_Forward.border = BorderFactory.createEmptyBorder()
        Arrow_Up = Arrow_Up.getScaledInstance(75,75,Image.SCALE_SMOOTH)
        move_Forward.icon = ImageIcon(Arrow_Up)
        move_Forward.addActionListener(this)  // Handle movement clicks.
        add(move_Forward)

        move_Backward = JButton()
        move_Backward.bounds = Rectangle(495, 415, 75, 75)
        move_Backward.font = baseFont
        move_Backward.background = Color(0,0,0,0)
        move_Backward.border = BorderFactory.createEmptyBorder()
        Arrow_Down = Arrow_Down.getScaledInstance(75,75,Image.SCALE_SMOOTH)
        move_Backward.icon = ImageIcon(Arrow_Down)
        move_Backward.addActionListener(this)
        add(move_Backward)

        move_Left = JButton()
        move_Left.bounds = Rectangle(420, 340, 75, 74)
        move_Left.font = baseFont
        move_Left.background = Color(0,0,0,0)
        move_Left.border = BorderFactory.createEmptyBorder()
        Arrow_Left = Arrow_Left.getScaledInstance(75,75,Image.SCALE_SMOOTH)
        move_Left.icon = ImageIcon(Arrow_Left)
        move_Left.addActionListener(this)
        add(move_Left)

        move_Right = JButton()
        move_Right.bounds = Rectangle(570, 340, 75, 75)
        move_Right.font = baseFont
        move_Right.background = Color(0,0,0,0)
        move_Right.border = BorderFactory.createEmptyBorder()
        Arrow_Right = Arrow_Right.getScaledInstance(75,75,Image.SCALE_SMOOTH)
        move_Right.icon = ImageIcon(Arrow_Right)
        move_Right.addActionListener(this)
        add(move_Right)

        yes = JButton()
        yes.bounds = Rectangle(750, 315, 100, 100)
        yes.font = baseFont
        yes.background = Color(0,0,0,0)
        yes.border = BorderFactory.createEmptyBorder()
        Yes = Yes.getScaledInstance(100,100,Image.SCALE_SMOOTH)
        yes.icon = ImageIcon(Yes)
        yes.addActionListener(this)  // Handle Yes clicks.
        add(yes)

        no = JButton()
        no.bounds = Rectangle(875, 315, 100, 100)
        no.font = baseFont
        no.background = Color(0,0,0,0)
        no.border = BorderFactory.createEmptyBorder()
        No = No.getScaledInstance(100,100,Image.SCALE_SMOOTH)
        no.icon = ImageIcon(No)
        no.addActionListener(this)  // Handle No clicks.
        add(no)

        // Disable movement buttons until the game starts.
        move_Forward.isEnabled = false
        move_Backward.isEnabled = false
        move_Left.isEnabled = false
        move_Right.isEnabled = false
    }

    /**
     * Update the UI based on the game state.
     * This function displays the current room's description.
     * It also checks if the game-ending condition is met.
     */
    fun updateView() {
        if (!app.gameStarted) {
            UI.text = "Welcome to Space Terror: \n Press No to read the rules or Press Yes to skip the rules and begin the game"
        } else {
            val currentRoom = app.currentRoom
            val desc = currentRoom?.description ?: "Unknown location"
            val baseDescription = "${currentRoom?.name}. $desc"


            // If in the Maintenance Room and the tool hasn't been found yet, show extra prompt.
            val extraPrompt = if (currentRoom?.name == "Maintenace Room" && !app.foundTool) {
                "\n\nYou see tools scattered around. Do you want to pick them up? Press Yes or No."
            } else {
                ""
            }
            UI.text = baseDescription + extraPrompt

            // Game-ending condition: If the player is in "Escape Pods" and has the tool repair kit.
            if (currentRoom?.name == "Escape Pod" && app.inventory.contains("Tools")) {
                endGame()  // End the game with congratulations.
                return
            }

        }
    }

    /**
     * Ends the game by congratulating the player.
     * Disables movement buttons to prevent further actions.
     */
    private fun endGame() {
        UI.font = Font(Font.SANS_SERIF, Font.ITALIC, 34)
        UI.text = "Congratulations! You used the Tools to repair the Escape pod and\nEscaped.\nYou survived"
        move_Forward.isEnabled = false
        move_Backward.isEnabled = false
        move_Left.isEnabled = false
        move_Right.isEnabled = false
        no.isEnabled = false
        yes.isEnabled = false
    }

    /**
     * Handle UI events from button clicks.
     */
    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {
            // Handling Yes button clicks.
            yes -> {
                if (!app.gameStarted) {
                    // Start the game.
                    app.gameStarted = true
                    enableMovementButtons()
                    app.currentRoom = app.rooms["Crew Quarters"]
                    updateView()
                } else {
                    // If in Maintenance Room and tool not found, pick up tool.
                    if (app.currentRoom?.name == "Maintenace Room" && !app.foundTool) {
                        app.foundTool = true
                        app.inventory.add("Tools")
                        UI.text = "You picked up the tools and put them in a box which you found lying just a few inches\naway from you ."
                    } else {
                        endGame()
                        return
                    }
                }
            }

            // Handling No button clicks.
            no -> {
                if (!app.gameStarted) {
                    UI.text = "Space Terror:\n The goal of the game is to try and escape a space station after a mysterious impact." +
                            "Move between rooms and search for clues or tools to escape.\n" +
                            "Thanks for Tryin my game! Press 'Yes' to begin."
                } else {
                    if (app.currentRoom?.name == "Maintenace Room" && !app.foundTool) {
                        UI.text = "You choose not to search the room."
                    } else {
                        UI.text = "You decide to stay put for now."
                    }
                }
            }

            // Movement button clicks.
            move_Forward -> { moveTo("North") }
            move_Backward -> { moveTo("South") }
            move_Left -> { moveTo("East") }
            move_Right -> { moveTo("West") }
        }
    }

    /**
     * Moves the player in a given direction.
     * Updates the current room and displays its description.
     * Re-enables movement buttons after moving.
     */
    private fun moveTo(direction: String) {
        app.currentRoom?.connections?.get(direction)?.let { room ->
            val firstVisit = !room.visited
            room.visited = true
            app.currentRoom = room

            val description = if (firstVisit) {
                "You moved $direction to ${room.name}.\n${room.description.trim()}"
            } else {
                "You moved $direction to ${room.name}. You remember your first visit here.\n${room.description.trim()}"
            }
            UI.text = description
        } ?: run {
            UI.text = "You can't go that way."
        }
        enableMovementButtons()
    }

    /**
     * Re-enables the movement buttons.
     */
    fun enableMovementButtons() {
        move_Forward.isEnabled = true
        move_Backward.isEnabled = true
        move_Left.isEnabled = true
        move_Right.isEnabled = true
    }

}


