%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Title: line_exp2par_2d                                                          %
% Author: Burkardt, J                                                             %
% Date: 2005                                                                      %
% Type: Source code                                                               %
% Availability: https://people.sc.fsu.edu/~jburkardt/m_src/geometry/geometry.html %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
function [x,y] = drawBisector(pointX,pointY,pointX2,pointY2)

length = sqrt((pointX-pointX2)^2+(pointY-pointY2)^2)+1;

midpointX = (pointX+pointX2)/2;
midpointY = (pointY+pointY2)/2;

[ f, g, x0, y0 ] = line_exp2par_2d ([pointX,pointY],[pointX2,pointY2]); %(Burkardt 2005)

f2 = g;
g2 = -f;
x0 = midpointX;
y0 = midpointY;

t = 0-20*length:0+20*length;

x = x0+f2*t;
y = y0+g2*t;
