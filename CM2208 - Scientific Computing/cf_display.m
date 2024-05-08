function cf_display(s, domain)

% CF_DISPLAY Display audio data in time or frequency domain
%
% CF_DISPLAY(S) displays the audio data specified by the input argument S
% in the time domain. The audio data is displayed as a plot of amplitude
% versus time.
%
% CF_DISPLAY(S, DOMAIN) displays the audio data in the specified domain.
% The input argument DOMAIN can be either 't' for time domain or 'f' for
% frequency domain.
%
% Inputs:
% -------
% - S: a cell array containing the audio data and sampling frequency. The
% audio data is stored as a column vector in S{1} and the sampling
% frequency is stored in S{2}.
% - DOMAIN: a string that specifies the domain in which to display the
% audio data. DOMAIN can be 't' for time domain or 'f' for
% frequency domain.
%
% Outputs:
% --------
% None.
%
% Example usage:
% --------------
% 1. To display audio data stored in a cell array variable named s
% in the time domain, type:
% cf_display(s)
%
% 2. To display audio data in the frequency domain, type:
% cf_display(s, 'f')
%
% Implementation:
% ---------------
% The CF_DISPLAY function uses the plot function in MATLAB to display the
% audio data. If DOMAIN is not specified, the audio data is displayed in
% the time domain. If DOMAIN is 't', the audio data is plotted as a plot of
% amplitude versus time. If DOMAIN is 'f', the Fourier transform of the
% audio data is computed and plotted as a plot of magnitude versus
% frequency. The Fourier transform is computed using the abs and fftshift
% functions in MATLAB. The frequency axis is created using the linspace
% function in MATLAB.

% Clear the current figure and axes
clf reset

% Set default value for domain argument to time
if nargin == 1 
    domain = "t";
end

% Display an amplitude time graph if domain == t
if strcmp(domain, "t")
    plot(s{1})
    xlabel("Time")
    ylabel("Amplitude")

% Display a frequency magnitude graph if domain == f
elseif strcmp(domain, "f")
    S = fftshift(abs(fft(s{1})));
    f = linspace(-s{2}/2, s{2}/2, length(s{1}));
    plot(f, S);
    xlabel("Frequency")
    ylabel("Magnitude")
end
end