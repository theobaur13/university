function cf_play(s,v)

% CF_PLAY Play audio data
%
% CF_PLAY(S) plays the audio data specified by the S input argument through
% the default audio output device at the default volume of 100%.
%
% CF_PLAY(S, V) plays the audio data specified by the S input argument
% through the default audio output device at the volume specified by the V
% input argument.
%
% Inputs:
% -------
% - S: a cell array containing the audio data to be played. The first
% element of the cell array should be a matrix containing the audio data.
% The second element of the cell array should be a scalar specifying the
% sampling rate of the audio data.
% - V: a scalar value between 0 and 100 that specifies the volume of the
% audio data to be played. The default value is 100.
%
% Outputs:
% --------
% None.
%
% Example usage:
% --------------
% 1. To play an audio matrix S at the default volume of 100%, type:
% cf_play(s)
%
% 2. To play an audio matrix named S at a volume of 50%, type:
% cf_play(s, 50)
%
% Implementation:
% ---------------
% The CF_PLAY function uses the soundsc function in MATLAB to play audio
% data through the default audio output device at the specified volume
% level. The soundsc function scales the amplitude of the audio data to fit
% within the range [-1, 1] based on the specified volume level. The audio
% data must be in the form of a matrix with each row corresponding to a
% sample and each column corresponding to a channel. The sampling rate of
% the audio data must be specified as a scalar value in the second element
% of the cell array input argument.

% Set default volume level to 100% if not specified
if nargin == 1
    v = 100;
end

% Extract audio data from cell array and mix down to mono
audio_data = s{1};
num_channels = size(audio_data, 2);
if num_channels > 1
    audio_data = mean(audio_data, 2);
end

% Adjust the volume of the audio data
audio_data = audio_data * (v / 100);

% Play audio data through default audio output device at specified volume level
soundsc(audio_data, s{2}, [-1, 1])

end