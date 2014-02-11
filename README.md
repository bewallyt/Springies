Springies
=========

A few things to note:

1. Everything should be implemented.

2. There's a 5 second time limit after pressing 'N' to generate a new assembly via a JFile Chooser. This is because we were unable to return a value from the button in JFileChooser. Instead Thread.sleep(5000) is called when "N" is pressed to allow the user enough time to choose an assembly.

3. Different assemblies require different physics constants (moreso spring constants). For instance, the constants for ball.xml differ greatly from those of daintywalker.xml.

4. ball.xml often is "zapped" off screen or requires multiple generations via "N" to have a decent run. The optimal magnitude for this assembly is: 

"magnitude = mySpringyness * (dist - myRestLength) * 1800;"

This is nested inside "springForce()" within the Spring class.

5. daintywalker.xml: "double magnitude = mySpringyness * (dist - myRestLength);"

  
