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
    // Constants defining any key values
   // val MAX_CLICKS = 10

    // Data fields
   // var clicks = 0

    // Application logic functions
    fun updateClickCount() {
       // clicks++
       // if (clicks > MAX_CLICKS) clicks = MAX_CLICKS
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
    private lateinit var lacation_1: JLabel
    private lateinit var lacation_2: JLabel
    private lateinit var lacation_3: JLabel
    private lateinit var lacation_4: JLabel
    private lateinit var lacation_5: JLabel
    private lateinit var Map: JLabel
    private lateinit var UI: TextField
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



        Map = JLabel("Map")
        Map.horizontalAlignment = SwingConstants.CENTER
        Map.bounds = Rectangle(50, 50, 300, 100)
        Map.font = baseFont
        add(Map)

        UI = TextField("......................................................")
        UI.bounds = Rectangle(400, 50, 550, 200)
        UI.foreground = Color.WHITE
        UI.background = Color.BLACK
        UI.font = baseFont
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
      //  if (app.clicks == app.MAX_CLICKS) {
      //      clicksLabel.text = "Max clicks reached!"
      //      clickButton.isEnabled = false
      //  }
      //  else {
      //      clicksLabel.text = "You clicked ${app.clicks} times"
      //      clickButton.isEnabled = true
      //  }
    }

    /**
     * Handle any UI events (e.g. button clicks)
     * Usually this involves updating the application model
     * then refreshing the UI view
     */
    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {
           /// clickButton -> {
           ///     app.updateClickCount()
           ///     updateView()
           /// }
        }
    }

}

