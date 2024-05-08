function s = cf_load2(filename)
% CF_LOAD2 Load an image and return image data
%   S = CF_LOAD2(filename) loads an image with the name FILENAME using
%   imread() and returns the image data as variable S
%
%   Inputs:
%   - FILENAME: a string specifying the path of the image file to be loaded
%   
%   Outputs:
%   - S: a matrix containing the image data for the file specified by
%   FILENAME using the IMREAD function
%
%   Example usage:
%   1. To load an image file called "image.jpg" in the current directory,
%   enter s = cf_load2("image.jpg") 
%
%   2. To load an image file called "image.jpg" located in a different
%   directory, enter s = cf_load2("folder/image.jpg")
%
%   Implementation:
%   Uses IMREAD function to read a specific image file and save its data
%   into variable S.
%
%   See also IMREAD

% Use imread to load image data from file
s = imread(filename);
end