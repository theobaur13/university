
function [x,y,r]  = firstCircle(centerX, centerY, circumferenceX, circumferenceY)
%The user enters a point, which is the centre of the circle. The user enters another point, which
%will lie on the circumference of the circle. Draw the points and the circle.

%Find the radius
r = sqrt(((circumferenceX-centerX)^2)+((circumferenceY-centerY)^2));

%Calculate circle
theta = linspace(0,2*pi);
x = centerX+r*cos(theta);
y = centerY+r*sin(theta);


%Plot circle
%plot(x,y)
