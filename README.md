# ProgBot

##### IMPORTANT!
__You need my [library](http://github.com/mrbesen/Y-Lib)!__

## To Do / To Add
- [x] Loops
- [x] Variables
- [ ] Random-generator
- [ ] output of Commands as Variable
- [ ] get Mouse coordinates as variable
- [ ] statements (if&while)
- [ ] GUI
- [ ] 'infinite' as nummber
- [x] functions

## Command-Overview
Command | Use
--------|----------
click t | fake a mouse click left Button: l, middle: m, right: r
loops | skroll down 
move x y | moves the mouse Cursor to the Cordinates x y for your security this command has an cooldown!
press k | "Presses" the Keyboard key
print a | prints a text to the console
pos | returns the Position of the mouse
wait s |make the Bot wait for (s/10) seconds
var $name | defines a new variable

__comments start with '#' or "//"!__

```
#Some Comment
//Second Comment
```

###Functions

######Define
```
functione name {
print "Test"
var $a = 1
}
name
print $a
```
This Code would define the function 'name' run it, and print the value of $a (1)

__Functiuns should be defined at the beginning.__ (All Code before will not be able to read the Function)


### Print
```
var $x = 5
print "Hello"
print $x
```
Example output from this script:

>Hello
>$x = 5

### Loops:
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

### Variables:
```
var $x = 5
loop $x
wait $x
loop end
```
This code would make a loop 5 times and wait for 0,5 seconds!

```
var $test1 = 5
var $test2 = $test1
var $test1 = 1
print $test2
print $test1
```
Example Output:
>$test2 = 5
>$test1 = 1

So, you can define a variable through another, but they don't stay "linked".

```
print $a
```
every undefined variable contains '0'.

######Predefinded Variables: 

$TIME -> the seconds since the 01.01.1970 00:00 UTC

###### Some Math
```
var $x = 3 * 2
print $x
print 3 - 2
print 20 / 5
var $b = 22 % 3
```
>$x = 6
>1
>4
>1

"Only" one Expression at the time allowed (else the awnser is maybe not correct!)

```
var $i = 1
loop 20
var $i = $i +1
print 2**$i
loop end
```
This code outputs the 2^x row.

__Allowed Functions:__
- + → add
- - → subtract
- * → multiply
- / → devide
- ** → pow
- % → mod


### Click
```
click l
wait 10
click r
wait 10
click m
```
This one would perform a Mouse Left click, then a right click and then a middle click.

### Wait
```
wait 10
```
This code would just wait for __1__ second.

#### Example script:
```
function dostuff {
move 0 0
click l
}
var $a = 1 + 2
loop $a
wait $a * 5
dostuff
print $a
pos
loop end
```