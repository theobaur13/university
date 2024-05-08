function [x,y,r2] = secondCircle(centerX, centerY, r, pointX, pointY)
%The user enters a point, which is the centre of the circle. The user enters another point, which
%will lie on the circumference of the circle. Draw the points and the circle.

%Find the radius
%r = sqrt(((circumferenceX-centerX)^2)+((circumferenceY-centerY)^2));

r2 = abs(sqrt((pointX-centerX)^2 + (pointY-centerY)^2) - r);

%Calculate circle
theta = linspace(0,2*pi);
x = pointX+r2*cos(theta);
y = pointY+r2*sin(theta);


%Plot circle
%plot(x,y)
