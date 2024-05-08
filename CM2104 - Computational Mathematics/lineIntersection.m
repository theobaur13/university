%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Title: lines_par_int_2d                                                         %
% Author: Burkardt, J                                                             %
% Date: 2005                                                                      %
% Type: Source code                                                               %
% Availability: https://people.sc.fsu.edu/~jburkardt/m_src/geometry/geometry.html %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
function [x,y] = lineIntersection(x1,y1,x2,y2)

[ f1, g1, x0_1, y0_1 ] = line_exp2par_2d ( [x1(1),y1(1)], [x1(2),y1(2)] );
[ f2, g2, x0_2, y0_2 ] = line_exp2par_2d ( [x2(1),y2(1)], [x2(2),y2(2)] );

[ t1, t2, pi ] = lines_par_int_2d ( f1, g1, x0_1, y0_1, f2, g2, x0_2, y0_2 ); %(Burkardt 2005)

if length(pi) == 0
    x = 0;
    y = 0;
else
    x = pi(1);
    y = pi(2);
end



