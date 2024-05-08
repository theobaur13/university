function cf_display2(s, domain)
% CF_DISPLAY2 Display image or Fourier transform of image
%
%   CF_DISPLAY2(S, DOMAIN) displays either the image matrix S or its Fourier
%   transform in the frequency domain. The display mode is determined by the
%   DOMAIN input argument, which can be either "s" for spatial domain or "f"
%   for frequency domain. If the DOMAIN input argument is not specified, or is
%   empty, the function defaults to displaying the spatial domain image.
%
%   Inputs:
%   - S: a matrix containing the image data to be displayed. The matrix size
%     depends on the dimensions of the image data.
%   - DOMAIN: a string indicating whether to display the spatial domain image
%     ("s") or its Fourier transform in the frequency domain ("f"). If DOMAIN
%     is not specified or empty, the function defaults to displaying the
%     spatial domain image.
%
%   Outputs:
%   None.
%
%   Example usage:
%   1. To display an image matrix variable named "myimage" in the spatial
%      domain, type:
%         cf_display2(myimage)
%
%   2. To display an image matrix variable named "myimage" in the frequency
%      domain, type:
%         cf_display2(myimage, "f")
%
%   Implementation:
%   The CF_DISPLAY2 function uses the imshow function in MATLAB to display the
%   image matrix or its Fourier transform, depending on the value of the DOMAIN
%   input argument. If DOMAIN is "s", the function displays the image matrix
%   directly using imshow. If DOMAIN is "f", the function performs a 2D FFT on
%   the image matrix using fft2, shifts the zero-frequency component to the
%   center of the spectrum using fftshift, calculates the magnitude of the
%   spectrum using abs, applies a logarithmic scaling to the magnitude values
%   using log(1 + x), and displays the result using imshow. Note that the
%   displayed FFT is not normalized, and the displayed image may appear
%   different depending on the size and content of the original image.
%
%   See also STRCMP, IMSHOW, FFT2, FFTSHIFT, ABS, LOG, IMSHOW

% Clear the current figure and axes
clf reset

% Set default value for domain argument
if nargin < 2 
    domain = "s";
end

% Display the image matrix or its Fourier transform depending on the domain
if strcmp(domain, "s")
    imshow(s)
elseif strcmp(domain, "f")
    % Display the Fourier transform of the image in the frequency domain
    S = fft2(s);
    S = fftshift(S);
    S = abs(S);
    S = log(1 + S);
    imshow(S, []);
end
end