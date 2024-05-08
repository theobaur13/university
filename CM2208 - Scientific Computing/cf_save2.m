function cf_save2(filename, s)
% CF_SAVE2 Save image data to a file
%   CF_SAVE2(FILENAME, S) saves image data, passed in through S
%   input arguement as an image file specified by the FILENAME input
%   arguement. The FILENAME input arguement also dictates the file type
%   Inputs:
%   - FILENAME: a string containing the name of the file to be saved into
%   followed by the file type, and the relative path of the file if it is
%   not to be saved into the current directory
%   - S: a matrix containing the image data for the file specified
%   
%   Outputs:
%   None.
%
%   Example usage:
%   1. To save an image file into "image.jpg" in the current directory as 
%   a .jpg file, enter cf_save2("image.jpg", s) 
%
%   2. To save an image file into "image.jpg" located in a different
%   directory as a .jpg file, enter cf_save2("folder/image.jpg", s)
%
%   Implementation:
%   Uses IMWRITE function to write image data into a file at a certain 
%   location, the image data is passed in through input arguement S and the
%   file name, location and type is specified in FILENAME
%
%   See also IMWRITE

% Use imwrite to save image data to file
imwrite(s,filename);
end