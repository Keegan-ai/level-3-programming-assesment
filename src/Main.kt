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


/**
 * Room class to represent different locations
 */
class Room(val name: String, val description: String) {
    val connections = mutableMapOf<String, Room>()

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
    val rooms = mutableMapOf<String, Room>()
    var currentRoom: Room? = null

    init {
        setupRooms()
    }

    private fun setupRooms() {
        val crewQuarters = Room("Crew Quarters", "You begin to wake up in the cabin quarters confused on what happen because last thing you knew is were sleeping. " +
                "As you start to come to your senses a alarm beeps saying Oxygen supply low advised to wear a suit until repa..zzz.zz..z are done." +
                "After hearing that you begin to put on a suit ")
        val hallway = Room("Hallway", "You enter the hallway and see a sign saying Maintenance Room south and Ahead is the Kitchen ")
        val controlRoom = Room("Control Room", "You enter the main control room. " +
                "You look around and see that the control room is dead, its systems offline. A few terminals sputter weakly, looping a broken distress signal: '...Mayday... impact detected... system failure...'  .")
        val kitchen = Room("Kitchen", "You enter the kitchen.You see that utensils floating around due to the station losing power.")
        val Escape_Pod = Room("Escape Pods","As You enter the escape pod room. You see that all escape pods are gone." +
                "You begin to panic think there isn't one here for you but luckily you find one unscathed. ")
        val Entrance = Room("Entrance"," You wonder where you are and you suddenly see a sign saying Escape Pods North and Section 1 South." )
        val Section_1 =Room("Section 1","You enter section 1 which is on the west most side of the space station." +
                " You look at the sign to see where you want to go next and the sign says South is where the Garbage Disposel and east is To go to the Control Room  ")
        val Section_2 = Room("Section 2","You enter section 2 which is on the east most side of the space station." +
                "You look at the sign to see where you want to go next and the sign says South is where the Kitchen is and West is to go to the Control Room.")
        val Garbage_Disposel = Room("Garbage Disposel","As you enter the Garbage Disposel room you look around and see trash lying everywhere which is not suprising." +
                "You see that there is a door going east towards the Hallway")
        val Maintenace_room = Room( "Maintenace Room","You enter maintenance room and see that it is in disarray. " +
                "Loose wires hang from the ceiling, sparking occasionally. " +
                "Toolboxes have toppled over, their contents scattered across the floor." +
                " A thick smell of burnt circuits lingers in the air")


        crewQuarters.connect("North", hallway)
        hallway.connect("South", crewQuarters)
        hallway.connect("East", controlRoom)
        hallway.connect("West", kitchen)
        controlRoom.connect("West", hallway)
        kitchen.connect("East", hallway)


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

        UI = JTextArea("Welcome to the game of Space Terror:Press Yes to continue ")
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
    }


    /**
     * Update the UI controls based on the current state
     * of the application model
     */
    fun updateView() {
    }


    /**
     * Handle any UI events (e.g. button clicks)
     * Usually this involves updating the application model
     * then refreshing the UI view
     */
    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {
            yes -> {
               // dialog_Handler() // Fetch next dialogue

            }
            No -> {
                //UI.text = "You decide to stay and look around more. The eerie silence of the station surrounds you."
            }
            move_Forward -> {
                   app.currentRoom!!.connections["North"]
                app.rooms
            }

            move_Backward -> {
                    app.currentRoom!!.connections["South"]
                app.rooms
            }

            move_Left -> {
                app.currentRoom!!.connections["East"]
                app.rooms

            }

            move_Right -> {
                app.currentRoom!!.connections["West"]
                app.rooms
            }
        }
    }


    fun map_Handler() {


    }

}


