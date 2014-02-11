Springies
=========

A few things to note:

1. Everything should be implemented. 
     
          "G"   --> toggles gravity
          "V"   --> toggles viscosity
          "M"   --> toggles center of mass
          "1"   --> toggles top wall repulsion
          "2"   --> toggles right wall repulsion
          "3"   --> toggles bottom wall repulsion
          "4"   --> toggles left wall repulsion
          "-"   --> decreases muscle amplitude
          "="   --> increases muscle amplitude
          "UP"  --> increases boundary
          "DOWN"--> decreases boundary
          "N"   --> generates new assembly
          "C"   --> clears all assemblies

2. There's a 5 second time limit after pressing 'N' to generate a new assembly via a JFile Chooser. This is because we were unable to return a value from the button in JFileChooser. Instead Thread.sleep(5000) is called when "N" is pressed to allow the user enough time to choose an assembly.

3. To make sure spring constants were high enough for the given environment, a conditional statement was placed within the spring parser to default any spring constant values lower than .01.

4. "ball.xml" often is "zapped" off screen or requires multiple generations via "N" to have a decent run.

5. "environment2.xml" was created and used to test these assemblies.


  
