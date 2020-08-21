NextClosestTime_681
    The natural way to represent the time is as an integer t (minutes) in the range 0 <= t < 24 * 60.
    Then the hours are t / 60, the minutes are t % 60, and each digit of the hours and minutes can be found by hours / 10, hours % 10 etc.
    E.g. time in minutes = 1174; hours = 1174/60 = 19h, minutes = 1174%60 = 34mins. 19/10 = 1, 19%10 = 9; 34/10 = 3, 34%10 = 4.
    
    