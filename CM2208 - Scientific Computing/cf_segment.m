function m = cf_segment(s,low,high)
%CF_SEGMENT Seperates foreground from background for an image
%
%   M = CF_SEGMENT(S, LOW, HIGH) applies thresholding to the input image S,
%   such that pixel intensities less than LOW are set to 0, pixel intensities
%   greater than HIGH are set to 0, and pixel intensities in between LOW and
%   HIGH are retained. The resulting binary image mask M is returned. If the
%   LOW and HIGH input arguments are not specified or are empty, default values
%   of 80 and 180 are used, respectively.
%
%   Inputs:
%   - S: a matrix containing the image data to be segmented. If S is a
%     colour image, the function converts it to grayscale using the
%     rgb2gray function.
%   - LOW: a value indicating the lower threshold for intensity values
%     in the input image. Pixel intensities less than LOW are set to 0 in the
%     resulting binary mask. If LOW is not specified or empty, a default value
%     of 80 is used.
%   - HIGH: a value indicating the upper threshold for intensity values
%     in the input image. Pixel intensities greater than HIGH are set to 0 in
%     the resulting binary mask. If HIGH is not specified or empty, a default
%     value of 180 is used.
%
%   Outputs:
%   - M: a binary mask image of the same size as the input image S, where pixel
%     values of 1 indicate the foreground in the segmented image,
%     and pixel values of 0 indicate the background.
%
%   Example usage:
%   1. To segment an image matrix called "s" with default
%      thresholds, type:
%         mask = cf_segment(s)
%
%   2. To segment an image matrix named "s" with custom
%      thresholds of 20 and 200, type:
%         mask = cf_segment(s, 20, 200)
%
%   Implementation:
%   The CF_SEGMENT function performs thresholding on the input image matrix S
%   to generate a binary image mask. If the input image is a colour image,
%   the function converts it to grayscale using the rgb2gray function. If the
%   LOW and HIGH input arguments are not specified or are empty, default values
%   of 80 and 180 are used, respectively. The function creates two binary masks
%   that correspond to the regions of the image with intensity values less than
%   LOW and greater than HIGH, respectively. These masks are added together to
%   produce a combined mask that represents the thresholded image. The combined
%   mask is then scaled to the range [0, 1] using double and / 255.0, and is
%   inverted using the "not" operator to obtain the final binary mask. Finally,
%   the function applies filling using the imfill function with
%   the "holes" option to fill any holes or gaps in the mask and produce a
%   complete segmentation of the image.

% Convert colour image to grayscale if necessary
if numel(size(s)) > 2
s = rgb2gray(s);
end

% Set default values for low and high thresholds if not specified
if nargin == 1
    low = 80;
    high = 180;
end

% Create binary masks for pixels below low and above high thresholds
mask_lo = zeros(size(s));
mask_lo(s < low) = 1;
mask_hi = zeros(size(s));
mask_hi(s > high) = 1;

% Combine the masks to create a binary image mask
im_m = ((mask_lo+mask_hi) .* double(s)) / 255.0;

% Fill in any holes or gaps in the binary mask and save to M
m = im_m == 0;
m = imfill(m,"holes");

end