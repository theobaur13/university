function s = cf_load(filename)
% CF_LOAD Load audio data from file
%
% CF_LOAD(FILENAME) loads audio data from a file specified by the FILENAME
% input argument. The audio file format is determined by the file extension
% in FILENAME. The function returns the audio data in a cell array with two
% elements, where the first element is the audio waveform as a column vector
% and the second element is the sample rate of the audio data.
%
% Inputs:
% - FILENAME: a string that specifies the full or relative path of the file
% to be loaded.
%
% Outputs:
% - S: a cell array containing the audio data loaded from the file specified
% by FILENAME. The cell array has two elements, where the first element is
% the audio waveform as a column vector and the second element is the
% sample rate of the audio data.
%
% Example usage:
%   1. To load an audio file named "audio.wav" located in the current working
%   directory, type:
%   s = cf_load('audio.wav')
%
% 2. To load an audio file named "audio.mp3" located in a subdirectory
%   named "folder" relative to the current working directory, type:
%   s = cf_load('folder/audio.mp3')
%
% Implementation:
% The CF_LOAD function uses the audioread function in MATLAB to read audio
% data from a file specified by FILENAME. The audio file format is determined
% by the file extension in FILENAME. The function then returns the audio data
% as a cell array with two elements, where the first element is the audio
% waveform as a column vector and the second element is the sample rate of
% the audio data.

% Use audioread to load audio data from file
[y,Fs] = audioread(filename);

% Store audio data in cell array
s = {y, Fs};
end