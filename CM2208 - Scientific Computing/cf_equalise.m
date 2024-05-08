function ss = cf_equalise(s,b)
% CF_EQUALISE Apply a multi-band equalization filter to an audio signal.
%
%   SS = CF_EQUALISE(S, B) applies a multi-band equalization filter to the
%   audio signal S, using the band gain values specified in the vector B.
%   The function returns the equalized audio signal as SS.
%
%   Inputs:
%   - S: a 2-element cell array containing the sound signal data and the
%   sampling frequency.
%   - B: a 11-element vector containing the gain values for each of the 11
%   frequency bands used in the equalisation filter. The frequency bands
%   center around the frequencies: 16 31.5 63 125 250 500 1000 2000 4000 
%   8000 16000
%
%   Outputs:
%   - SS: a 2-element cell array containing the equalised sound signal data
%   and the sampling frequency. The data in SS is a column vector.
%
%   Example usage:
%   1. To equalise the sound signal contained in a cell array variable named
%   "s" with gain values specified in a vector variable named "b", type:
%   ss = cf_equalise(s, b)
%
%   Implementation:
%   The CF_EQUALISE function splits the sounds data from S{1} into 11 bands
%   that center around the frequencies: 16 31.5 63 125 250 500 1000 2000 
%   4000 8000 16000. A low shelf filter is applied to the first band, peak
%   filters are applied to bands 2-10 and a high shelf filter is applied to
%   band 11. These filters are given in __________. The gain values from B
%   are then applied to each band, with the index of each gain value
%   corresponding to a single band. This process is repeated for each audio
%   track.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%
%   References:
%   Holters, M. 2011. Filters and delays. In: DAFX: Digital Audio Effects 
%   Second Edition. Edited by Z̈olzer, U. Chichester, West Sussex. John 
%   Wiley & Sons Ltd. 63, 65-66
%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Define center frequencies of octave bands
bands = [16 31.5 63 125 250 500 1000 2000 4000 8000 16000]; 

% Get sampling rate and audio data from s
Fs = s{2};
s_data = s{1};

% Apply equalization filters to each octave band
for i = 1:length(bands)
    G = b(i);
    Wc = (2*bands(i))/Fs;
    
    % Apply low shelf filter to first band
    if i == 1
        for j = 1:size(s_data,2)
            x = s_data(:,j);

            % Applies a low-frequency shelving filter to the input signal x.
            % Wc is the normalized cut-off frequency 0<Wc<1, i.e. 2*fc/fS
            % G is the gain in dB
            % (Holters 2011)

            V0 = 10^(G/20); H0 = V0 - 1;
            if G >= 0
              c = (tan(pi*Wc/2)-1) / (tan(pi*Wc/2)+1);
            else
              c = (tan(pi*Wc/2)-V0) / (tan(pi*Wc/2)+V0);
            end
            xh = 0;
            for n=1:length(x)
              xh_new = x(n) - c*xh;
              ap_y = c * xh_new + xh;
              xh = xh_new;
              y(n) = 0.5 * H0 * (x(n) + ap_y) + x(n);
            end

            % Replace original audio data with filtered data
            s_data(:,j) = y;
        end

    % Apply peak filter to middle bands
    elseif i >= 2 && i <= 10
        for j = 1:size(s_data,2)
            x = s_data(:,j);
            Wb = (2*bands(i-1))/Fs;
            
            % Applies a peak filter to the input signal x.
            % Wc is the normalized center frequency 0<Wc<1, i.e. 2*fc/fS.
            % Wb is the normalized bandwidth 0<Wb<1, i.e. 2*fb/fS.
            % G is the gain in dB.
            % (Holters 2011)
            
            V0 = 10^(G/20); H0 = V0 - 1;
            if G >= 0
              c = (tan(pi*Wb/2)-1) / (tan(pi*Wb/2)+1);
            else
              c = (tan(pi*Wb/2)-V0) / (tan(pi*Wb/2)+V0);
            end
            d = -cos(pi*Wc);
            xh = [0, 0];
            for n=1:length(x)
              xh_new = x(n) - d*(1-c)*xh(1) + c*xh(2);
              ap_y = -c * xh_new + d*(1-c)*xh(1) + xh(2);
              xh = [xh_new, xh(1)];
              y(n) = 0.5 * H0 * (x(n) - ap_y) + x(n);
            end

            % Replace original audio data with filtered data
            s_data(:,j) = y;
        end

    % Apply high shelf filter to last band
    else
        for j = 1:size(s_data,2)
            x = s_data(:,j);

            % Applies a low-frequency shelving filter to the input signal x.
            % Wc is the normalized cut-off frequency 0<Wc<1, i.e. 2*fc/fS
            % G is the gain in dB
            % (Holters 2011)

            V0 = 10^(G/20); H0 = V0 - 1;
            if G >= 0
              c = (tan(pi*Wc/2)-1) / (tan(pi*Wc/2)+1);
            else
              c = (tan(pi*Wc/2)-V0) / (tan(pi*Wc/2)+V0);
            end
            xh = 0;
            for n=1:length(x)
              xh_new = x(n) - c*xh;
              ap_y = c * xh_new + xh;
              xh = xh_new;
              y(n) = 0.5 * H0 * (x(n) + ap_y) - x(n);
            end

            % Replace original audio data with filtered data
            s_data(:,j) = y;
        end
    end
end

ss = {s_data,Fs};
end