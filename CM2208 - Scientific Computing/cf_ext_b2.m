function m = cf_ext_b2(s, n)
% CF_EXT_B2 Enhance the contrast of an image by applying histogram equalization on its L channel in the Lab color space.
%
%   CF_EXT_B2(S, N) applies histogram equalization on the input image S in 
%   the to enhance its contrast. The resulting image is returned as an 
%   output. N is the contrast factor representing the degree of contrast
%   applied
%
%   Inputs:
%   - S: a matrix containing the image data to be displayed. The matrix size
%     depends on the dimensions of the image data.
%   - N: A normalization factor used to scale the L channel after histogram equalization. Default value is 100.
%
%   Outputs:
%   - M: The resulting contrast-enhanced image.
%
%   Example usage: 
%   To enhance the contrast of a colour image matrix variable S using a contrast level of 2, type: m = cf_ext_b2(s, 2)
%
%   Implementation:
%   The CF_EXT_B2 function first converts the input image to the LAB color
%   space using the rgb2lab function in MATLAB. The L channel of the LAB color
%   space is extracted and normalized to the range [0,1]. Adaptive histogram
%   equalization is then applied to the L channel using the adapthisteq
%   function in MATLAB. The resulting L channel is multiplied by the
%   quantization level N and combined with the a and b channels of the LAB color
%   space. Finally, the enhanced image is converted back to the RGB color space
%   using the lab2rgb function in MATLAB.

% Convert the input image to the Lab color space.
s = im2double(s);
s_lab = rgb2lab(s);
L = s_lab(:,:,1)/n;

% Apply histogram equalization on the normalized L channel using the 
% adapthisteq function.
s_histeq = s_lab;
s_histeq(:,:,1) = adapthisteq(L)*n;
s_histeq = lab2rgb(s_histeq);

m = s_histeq;

end