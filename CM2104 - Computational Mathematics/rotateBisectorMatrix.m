function rotationMatrix = rotateBisectorMatrix(x,y)
%work out change in x and change in y to find angle intbetween x axis and
%line using trigonometry
yDiff = y(2)-y(1);
xDiff = x(2)-x(1);
angle = atan(yDiff/xDiff);
theta = -1*angle;

rotationMatrix = [cos(theta) -1*sin(theta) 0 ; sin(theta) cos(theta) 0 ; 0 0 1];