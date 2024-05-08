%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Title: line_exp2par_2d                                                          %
% Author: Burkardt, J                                                             %
% Date: 2005                                                                      %
% Type: Source code                                                               %
% Availability: https://people.sc.fsu.edu/~jburkardt/m_src/geometry/geometry.html %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Title: lines_par_int_2d                                                         %
% Author: Burkardt, J                                                             %
% Date: 2005                                                                      %
% Type: Source code                                                               %
% Availability: https://people.sc.fsu.edu/~jburkardt/m_src/geometry/geometry.html %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
function [x,y] = closestPointLine(xArray,yArray, xPoint, yPoint)

[ f1, g1, x0, y0 ] = line_exp2par_2d ([xArray(1),yArray(1)],[xArray(2),yArray(2)]); %(Burkardt 2005)
f2 = g1;
g2 = -f1;
x0_2 = xPoint;
y0_2 = yPoint;

[ t1, t2, pi ] = lines_par_int_2d ( f1, g1, x0, y0, f2, g2, x0_2, y0_2 ); %(Burkardt 2005)

if size(pi) == 0
    x = 0;
    y = 0;
else
    x = pi(1);
    y = pi(2);
end