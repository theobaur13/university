#!/usr/bin/python3
import cgi, cgitb
import calendar
import datetime
form = cgi.FieldStorage()
y = int(form.getvalue("easternumber"))
numFormat = form.getvalue("styleoption")
#y=2000
#numFormat = "verbose"

def calculation(y):
    a = y % 19   
    b = y // 100   
    c = y % 100   
    d = b // 4   
    e = b % 4   
    g = (8 * b + 13) // 25   
    h = (19 * a + b - d - g + 15) % 30   
    j = c // 4
    k = c % 4
    m = (a + 11 * h) // 319
    r = (2 * e + 2 * j - k - h + m + 32) % 7                        #y=year n=month p=day
    n = (h - m + r + 90) // 25
    p = (h - m + r + n + 19) % 32
    formatvalue(y,n,p)

def numerical(y,n,p):
    return str(p)+"/"+str(n)+"/"+str(y) 

def verbose(y,n,p):
    if p[-1] == "1" and not p =="11":
        p= str(p)+"<sup>st</sup>"
        
    elif p[-1] == "2" and not p =="12":
        p= str(p)+"<sup>nd</sup>"
        
    elif p[-1] == "3" and not p =="13":
        p= str(p)+"<sup>rd</sup>"
        
    else:
        p= str(p)+"<sup>th</sup>"
        
    return str(p)+" "+str(calendar.month_name[int(n)])+" "+str(y)
    
def formatvalue(y,n,p):
    if numFormat == "number":
        print ("<h1>"+numerical(y,n,p)+"</h1>")
    elif numFormat == "verbose":
        print ("<h1>"+verbose(y,n,str(p))+"</h1>")
    else:
        print ("<h1>"+numerical(y,n,p)+"</h1>")
        print ("or...")
        print ("<h1>"+verbose(y,n,str(p))+"</h1>")



print('Content-Type: text/html; charset=utf-8')
print('')
print('<!DOCTYPE html>')
print('<html>')
print('<head>')
print('<link rel="stylesheet" type="text/css" href="../css/style.css"/>')
print('<title> Easter Algorithm </title>')
print('<meta charset="utf-8"/>')
print('</head>')
print('<body>')
print('<div class="box">')
print('<br>')
print('The correct date is:')
calculation(y)
print ('<form><input type="button" value="Go back!" onclick="history.back()"> </form>')
print('<br>')
print('</div>')
print('</body>')
print('</html>')

