Scenario:  A Player gets a strike on 1st roll, checking that bonus on next 2 rolls are added 
 
Given A game has started with players named "Tony" for 2 Frames ")
When When player takes his 1st roll, he gets a 10
When When player takes his 2nd roll, he gets a 3
When When player takes his 3rd roll, he gets a 6
Then His score for that frame should be 19
 
Scenario:  A Player gets a spare  on 2 rolls, checking that an extra roll is given 
 
Given A game has started with players named "Sam" for 2 Frames ")
When When player takes his 1st roll, he gets a 6
When When player takes his 2nd roll, he gets a 4
When When player takes his 3rd roll, he gets a 6
Then His score for that frame should be 16

Scenario:  A Player gets a Strike  on 1st roll,first frame, he then gets a strike on 1st roll 2nd frame
 checking that no bonus is added to the first frame 
 
Given A game has started with players named "Sam" for 2 Frames ")
When When player takes his 1st roll, he gets a 10
When When player takes his 2nd roll, he gets a 10
Then His score for that frame should be 10