package com.wadhams.music.reorg.app

import com.mpatric.mp3agic.ID3v2
import com.mpatric.mp3agic.Mp3File
import org.jaudiotagger.audio.AudioFile
import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.tag.Tag
import org.jaudiotagger.tag.FieldKey
import java.util.regex.Pattern
import groovy.io.FileType

class MusicApp2 {
	static void main(String[] args) {
		println 'MusicApp started...'
		println ''

		MusicApp2 app = new MusicApp2()
		app.execute()

		println 'MusicApp2 ended.'
	}

	def execute() {
		File baseDir = new File('D:\\20190731-DESKTOP-NCVCJB5\\Saved_HDD\\Unique_Music\\MORE\\ARTIST')

		String artist
		String album
		String title
		
		def ampersand = ~/&/						//ampersand
		def doubleQuote = ~/"/						/*double quote*/
		def dotSlashes = ~/[.\/\\]/					//dot, forward and back slash
		def illegals = ~/[#%{}<>*?$!:@+`|=\[\]]/	//illegal filename characters
		def valids = ~/[a-zA-Z0-9, '()-_]/
		
		baseDir.traverse(type: FileType.FILES) {f ->
			String fn = f.name
			//println fn

			if (fn.endsWith('.mp3')) {
				Mp3File mp3file = new Mp3File(f)
				if (mp3file.hasId3v2Tag()) {
					ID3v2 id3v2Tag = mp3file.getId3v2Tag()
					artist = id3v2Tag.getArtist()

					String testAlbum = id3v2Tag.getAlbum()
					if (testAlbum) {
						album = testAlbum.trim()
					}
					else {
						album = ''
					}

					title = id3v2Tag.getTitle()
				}
			}
			else if (fn.endsWith('.wma')) {
				AudioFile audioFile = AudioFileIO.read(f)
				Tag tag = audioFile.getTag()
				if (tag != null) {
					artist = tag.getFirst(FieldKey.ARTIST)

					String testAlbum = tag.getFirst(FieldKey.ALBUM)
					if (testAlbum) {
						album = testAlbum.trim()
					}
					else {
						album = ''
					}

					title = tag.getFirst(FieldKey.TITLE)
				}
			}
			
			String artist2
			String album2
			String title2
	
			if (artist) {
				artist2 = artist.replaceAll(ampersand, 'and')
				artist2 = artist2.replaceAll(doubleQuote, '\'')
				artist2 = artist2.replaceAll(dotSlashes, '-')
				artist2 = artist2.replaceAll(illegals, '')
				artist2 = artist2.replaceAll(valids, '').trim()
			}
			if (album) {
				album2 = album.replaceAll(ampersand, 'and')
				album2 = album2.replaceAll(doubleQuote, '\'')
				album2 = album2.replaceAll(dotSlashes, '-')
				album2 = album2.replaceAll(illegals, '')
				album2 = album2.replaceAll(valids, '').trim()
			}
			if (title) {
				title2 = title.replaceAll(ampersand, 'and')
				title2 = title2.replaceAll(doubleQuote, '\'')
				title2 = title2.replaceAll(dotSlashes, '-')
				title2 = title2.replaceAll(illegals, '')
				title2 = title2.replaceAll(valids, '').trim()
			}
			
			if (artist2 || album2 || title2) {
				println "fn: ${f.getAbsolutePath()}"
				println "\tartist: $artist  album: $album  title: $title"
			}
			
		}
	}

}
