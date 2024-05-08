function cf_ext_a2(s)
% CF_EXT_A2 Extract edges from an image using the Sobel operator.
%
%   CF_EXT_A2(S) applies the Sobel operator to detect edges in the input
%   image S and displays the resulting edge map using the imshow function.
%   If S is not a grayscale image, it will be converted to grayscale before
%   applying the Sobel operator.
%
%   Inputs:
%   - S: a matrix containing the image data to be displayed. The matrix size
%     depends on the dimensions of the image data.
%
%   Outputs:
%       None.
%
%   Example usage:
%       To extract edges from an image matrix S, type cf_ext_a2(s)
%
%   Implementation:
%   The CF_EXT_A2 function performs edge detection on an image matrix and
%   displays the resulting edges. If the input image is a colour image,
%   the function converts it to grayscale using the rgb2gray function. The
%   function then applies the Sobel operator to detect the edges in the
%   image. It then ues IMSHOW to display the edges.

% If the input image is not grayscale, convert it to grayscale
if numel(size(s)) > 2
    s = rgb2gray(s);
end

% Apply the Sobel operator to detect edges in the image
edges = edge(s, 'Sobel');

% Display the edges
imshow(edges)

end