# ProgBot

##### IMPORTANT!
__you need my [library](http://github.com/mrbesen/Y-Lib)!__


#### Commands:
Command | Use
--------|----------
click t | fake a mouse click left Button: l, middle: m, right: r
loops | skroll down 
move x y | moves the mouse Cursor to the Cordinates x y for your security this command has an cooldown!
press k | "Presses" the Keyboard key
pos | returns the Position of the mouse
wait s |make the Bot wait for (s/10) seconds

comments are everything else than a command

__new: loops!__
You can make a loop like:
```
loop 5
press a
wait 500
loop end
```
this would press the 'a'-key sleep and for 50 seconds and do this 5 times

You can make a loop in a loop:
```
loop 4
wait 3
loop 10
press d
loop end
press w
loop end
```

You can also make an infinite loop:
```
loop infinite
wait 10
press a
move 0 0
loop end
```



example script:
```
loop 3
wait 15
move 0 0
click l
pos
loop end
```