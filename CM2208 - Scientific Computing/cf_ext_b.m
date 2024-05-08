function ss = cf_ext_b(s)
% CF_EXT_B Apply a wah-wah effect to an audio signal
%
%   SS = CF_EXT_B(S) applies a wah-wah effect to an audio signal
%   specified by the input argument S, and returns the modified signal in
%   SS. The wah-wah effect is created by applying a bandpass filter whose
%   centre frequency oscillates between a minimum and maximum frequency over
%   time. The resulting effect is similar to the sound of a wah-wah guitar
%   pedal.
%
%   Inputs:
%   - S: a cell array containing the audio signal to which the wah-wah effect
%   will be applied. The cell array has the following format:
%   {S, FS} where S is the audio signal matrix, and FS is the sample rate (in Hz).
%
%   Outputs:
%   - SS: a cell array containing the wah-wah modified audio signal. The cell
%   array has the same format as S.
%
%   Example usage:
%   Apply a wah-wah effect to an audio signal S 
%   ss = cf_ext_b(s);
%
%   Implementation:
%   The CF_EXT_B function applies a wah-wah effect to an audio signal by
%   implementing a bandpass filter whose centre frequency oscillates between a
%   minimum and maximum frequency over time. The effect is created by the
%   difference equation of a second-order IIR filter. The filter coefficients
%   must be recalculated each time the centre frequency changes. The function
%   uses the sample rate (in Hz) to determine the change in centre frequency
%   per sample. The amplitude of the bandpass filtered signal is normalised to
%   prevent distortion.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%
%   References:
%   Marshall, D. (n.d.) Digital Audio Effects [Lecture Slides]. 
%   Available at: https://users.cs.cf.ac.uk/Dave.Marshall/CM0268/PDF/10_CM0268_Audio_FX.pdf
%   [Accessed: 17 March 2022].
%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

Fs = s{2};
x = s{1};
s_data = zeros(size(x));

% (Marshall n.d.)
% Set effect coefficients
wah_f = 2000; %How many Hz per second are cycles through
min_f = 500; %Min cut off freq
max_f = 4000; %Max cut off freq
damp_fact = 0.05;

% change in centre frequency per sample (Hz)
delta = wah_f/Fs;

% create triangle wave of centre frequency values
Fc=min_f:delta:max_f;
while(length(Fc) < length(x) )
    Fc=[ Fc (max_f:-delta:min_f) ];
    Fc=[ Fc (min_f:delta:max_f) ];
end

% trim tri wave to size of input
Fc = Fc(1:length(x));

% difference equation coefficients must be recalculated each time Fc changes
F1 = 2*sin((pi*Fc(1))/Fs);
Q1 = 2*damp_fact;

for j = 1:size(x,2)
    x_column = x(:, 2);
    % create emptly out vectors
    yh=zeros(size(x_column));
    yb=zeros(size(x_column));
    yl=zeros(size(x_column));
    % first sample, to avoid referencing of negative signals
    yh(1) = x_column(1);
    yb(1) = F1*yh(1);
    yl(1) = F1*yb(1);
    
    % apply difference equation to the sample
    for n=2:length(x_column)
        yh(n) = x_column(n) - yl(n-1) - Q1*yb(n-1);
        yb(n) = F1*yh(n) + yb(n-1);
        yl(n) = F1*yb(n) + yl(n-1);
        F1 = 2*sin((pi*Fc(n))/Fs);
    end
    %normalise
    maxyb = max(abs(yb));
    yb = yb./maxyb;
    s_data(:,j) = yb;
end
ss = {s_data, Fs};

end