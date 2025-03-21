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
 * The application class (model)
 * This is the place where any application data should be
 * stored, plus any application logic functions
 */
class App() {
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
        move_Forward.bounds = Rectangle(495,265,75,75)
        move_Forward.font = baseFont
        move_Forward.addActionListener(this)     // Handle any clicks
        add(move_Forward)

        move_Backward = JButton("v")
        move_Backward.bounds = Rectangle(495,415,75,75)
        move_Backward.font = baseFont
        move_Backward.addActionListener(this)     // Handle any clicks
        add(move_Backward)

        move_Left = JButton("<")
        move_Left.bounds = Rectangle(420,340,75,74)
        move_Left.font = baseFont
        move_Left.addActionListener(this)     // Handle any clicks
        add(move_Left)

        move_Right = JButton(">")
        move_Right.bounds = Rectangle(570,340,75,75)
        move_Right.font = baseFont
        move_Right.addActionListener(this)     // Handle any clicks
        add(move_Right)

        yes = JButton("Yes")
        yes.bounds = Rectangle(750,315,100,100)
        yes.font = baseFont
        yes.addActionListener(this)     // Handle any clicks
        add(yes)

        No = JButton("No")
        No.bounds = Rectangle(875,315,100,100)
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
            UI.text = "You Journey Begins on a space station orbiting earth." +
                    "You are part of a crew called the SS Galactic where you've been assigned to test various chemicals which were too dangerous to use on earth." +
                    "One Day a explosion happened and the next thing you knew you woke up in the crew quarters. " +
                    " .........................................................................................................." +
                    ":Press Yes if you wish to look around or No to stand up and move to the next area."
            UI.text = "After looking around for awhile the only thing you can see are bunks bed fallen over and One of your crew mates lying on the floor." +
                   "Press Yes to continue or No to keep looking "
             updateView()
            }
            move_Forward -> {

            }
        }
    }


    fun Handles_Map(){

    }
    fun Handles_Dialogue(){

    }

}

