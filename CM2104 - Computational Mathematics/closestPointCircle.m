%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Title: circle_imp_line_par_int_2d                                               %
% Author: Burkardt, J                                                             %
% Date: 2005                                                                      %
% Type: Source code                                                               %
% Availability: https://people.sc.fsu.edu/~jburkardt/m_src/geometry/geometry.html %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
function [returnX,returnY] = closestPointCircle(circlePointsArray,pointX,pointY)
%arguments are two points, and an array of circle data, 
%returns the point on the circle closest to the points which will be on the 
%circumference on one of the circles
x = [];
y = [];

for i=1:size(circlePointsArray,1) 
    centerX = circlePointsArray(i,1);
    centerY = circlePointsArray(i,2);
    r = circlePointsArray(i,3);
    f = pointX-centerX;
    g = pointY-centerY;
    x0 = centerX;
    y0 = centerY;

    [ num_int, p ] = circle_imp_line_par_int_2d ( r, [centerX,centerY], x0, y0, f, g ); %(Burkardt 2005)
    
    if any(isnan(p), 'all')
        x = 0;
        y = 0;
    end


    for i=1:num_int
        x = [x;p(1,i)];
        y = [y;p(2,i)];
    end
end

distances = [];

for i=1:length(x)
    distances(end+1) = sqrt((pointX-x(i))^2 + (pointY-y(i))^2);
end

index = find(distances==min(distances));

returnX = x(index);
returnY = y(index);